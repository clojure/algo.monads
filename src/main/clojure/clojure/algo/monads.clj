;; Monads in Clojure

;; Copyright (c) Konrad Hinsen, 2011. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns
  ^{:author "Konrad Hinsen"
     :see-also [["https://github.com/khinsen/monads-in-clojure/blob/master/PART1.md" "Monad tutorial part 1"]
                ["https://github.com/khinsen/monads-in-clojure/blob/master/PART2.md" "Monad tutorial part 2"]
                ["https://github.com/khinsen/monads-in-clojure/blob/master/PART3.md" "Monad tutorial part 3"]
                ["https://github.com/khinsen/monads-in-clojure/blob/master/PART4.md" "Monad tutorial part 4"]
                ["http://www.clojure.net/tags.html#monads-ref" "Blog posts on monads in Clojure"]]
     :doc "This library contains the most commonly used monads as well
           as macros for defining and using monads and useful monadic
           functions."}
  clojure.algo.monads
  (:require [clojure.set])
  (:use [clojure.tools.macro
         :only (with-symbol-macros defsymbolmacro name-with-attributes)]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Defining monads
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmacro monad
  "Define a monad by defining the monad operations. The definitions
   are written like bindings to the monad operations m-bind and
   m-result (required) and m-zero and m-plus (optional)."
  [operations]
  `(let [~'m-bind   ::this-monad-does-not-define-m-bind
         ~'m-result ::this-monad-does-not-define-m-result
         ~'m-zero   ::this-monad-does-not-define-m-zero
         ~'m-plus   ::this-monad-does-not-define-m-plus
         ~@operations]
     {:m-result ~'m-result
      :m-bind ~'m-bind 
      :m-zero ~'m-zero
      :m-plus ~'m-plus}))

(defmacro defmonad
  "Define a named monad by defining the monad operations. The definitions
   are written like bindings to the monad operations m-bind and
   m-result (required) and m-zero and m-plus (optional)."

  ([name doc-string operations]
   (let [doc-name (with-meta name {:doc doc-string})]
     `(defmonad ~doc-name ~operations)))

  ([name operations]
   `(def ~name (monad ~operations))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Using monads
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- ensure-items [n steps]
  "Ensures there are at least n elements on a list, will fill up with nil
  values when list is not big enough."
  (take n (concat steps (repeat nil))))

(defn- each3-steps [steps]
  "Transforms a list in a list of triples following the form:
   [a b c] => [[a b c] [b c nil] [c nil nil]]."
  (let [n (count steps)]
  (map vector (ensure-items n steps)
              (ensure-items n (rest steps))
              (ensure-items n (rest (rest steps))))))

(def ^:private prepare-monadic-steps
     #(->> % (partition 2) reverse each3-steps))

(defn- if-then-else-statement
  "Process an :if :then :else steps when adding a new
  monadic step to the mexpr."
  [[[_          else-mexpr]
    [then-bform then-mexpr]
    [if-bform   if-conditional]] mexpr continuation]
    (cond
      (and (identical? then-bform :then)
           (identical? if-bform   :if))
        `(if ~if-conditional
          ~(reduce continuation
                   mexpr
                   (prepare-monadic-steps then-mexpr))
          ~(reduce continuation
                   mexpr
                   (prepare-monadic-steps else-mexpr)))
      :else
       (throw (Exception. "invalid :if without :then and :else"))))

(defn- merge-cond-branches [cond-branches]
  (let [merger (fn [result cond-branch]
                  (-> result
                      (conj (first cond-branch))
                      (conj (second cond-branch))))]
    (reduce merger [] cond-branches)))

(defn cond-statement
  "Process a :cond steps when adding a new monadic step to the mexpr."
  [expr mexpr continuation]
  (let [cond-sexps (partition 2 expr)
        result (for [[cond-sexp monadic-sexp] cond-sexps]
                     (list cond-sexp
                           (reduce continuation
                                   mexpr
                                   (prepare-monadic-steps monadic-sexp))))]
      `(cond ~@(merge-cond-branches result))))

(defn- add-monad-step
  "Add a monad comprehension step before the already transformed
   monad comprehension expression mexpr."
  [mexpr steps]
  (let [[[bform expr :as step] & _] steps]
    (cond
      (identical? bform :when)  `(if ~expr ~mexpr ~'m-zero)
      (identical? bform :let)   `(let ~expr ~mexpr)
      (identical? bform :cond)  (cond-statement expr mexpr add-monad-step)
      (identical? bform :then)  mexpr
      ; ^ ignore :then step (processed on the :else step)
      (identical? bform :if)    mexpr
      ; ^ ignore :if step (processed on the :else step)
      (identical? bform :else)
        (if-then-else-statement steps mexpr add-monad-step)
      :else
        (list 'm-bind expr (list 'fn [bform] mexpr)))))

(defn- monad-expr
  "Transforms a monad comprehension, consisting of a list of steps
   and an expression defining the final value, into an expression
   chaining together the steps using :bind and returning the final value
   using :result. The steps are given as a vector of
   binding-variable/monadic-expression pairs."
  [steps expr]
  (when (odd? (count steps))
    (throw (Exception. "Odd number of elements in monad comprehension steps")))

  (let [rsteps  (prepare-monadic-steps steps)
        [[lr ls] & _] (first rsteps)]
    (if (= lr expr)
      ; Optimization: if the result expression is equal to the result
      ; of the last computation step, we can eliminate an m-bind to
      ; m-result.
      (reduce add-monad-step
        ls
        (rest rsteps))
      ; The general case.
      (reduce add-monad-step
        (list 'm-result expr)
        rsteps))))

(defmacro with-monad
  "Evaluates an expression after replacing the keywords defining the
   monad operations by the functions associated with these keywords
   in the monad definition given by name."
  [monad & exprs]
  `(let [name#      ~monad
         ~'m-bind   (:m-bind name#)
         ~'m-result (:m-result name#)
         ~'m-zero   (:m-zero name#)
         ~'m-plus   (:m-plus name#)]
     (with-symbol-macros ~@exprs)))

(defmacro domonad
  "Monad comprehension. Takes the name of a monad, a vector of steps
   given as binding-form/monadic-expression pairs, and a result value
   specified by expr. The monadic-expression terms can use the binding
   variables of the previous steps.
   If the monad contains a definition of m-zero, the step list can also
   contain conditions of the form :when p, where the predicate p can
   contain the binding variables from all previous steps.
   A clause of the form :let [binding-form expr ...], where the bindings
   are given as a vector as for the use in let, establishes additional
   bindings that can be used in the following steps."
  ([steps expr]
    (monad-expr steps expr))
  ([name steps expr]
    (let [mexpr (monad-expr steps expr)]
      `(with-monad ~name ~mexpr))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Defining functions used with monads
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmacro defmonadfn
  "Like defn, but for functions that use monad operations and are used inside
   a with-monad block."
  {:arglists '([name docstring? attr-map? args expr]
               [name docstring? attr-map? (args expr) ...])}
  [name & options]
  (let [[name options]  (name-with-attributes name options)
        fn-name (format "m+%s+m" (str name))
        fn-sym (symbol fn-name)
        full-sym (symbol (str *ns*) fn-name)
        make-fn-body    (fn [args expr]
                          (list (vec (concat ['m-bind 'm-result
                                              'm-zero 'm-plus] args))
                                (list `with-symbol-macros expr)))]
    (if (list? (first options))
      ; multiple arities
      (let [arglists        (map first options)
            exprs           (map second options)
            ]
        `(do
           (defsymbolmacro ~name (partial ~full-sym ~'m-bind ~'m-result
                                                   ~'m-zero ~'m-plus))
           (defn ~fn-sym ~@(map make-fn-body arglists exprs))))
      ; single arity
      (let [[args expr] options]
        `(do
           (defsymbolmacro ~name (partial ~full-sym ~'m-bind ~'m-result
                                                   ~'m-zero ~'m-plus))
           (defn ~fn-sym ~@(make-fn-body args expr)))))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Commonly used monad functions
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define the four basic monad operations as symbol macros that
; expand to their unqualified symbol equivalents. This makes it possible
; to use them inside macro templates without having to quote them.
(defsymbolmacro m-result m-result)
(defsymbolmacro m-bind m-bind)
(defsymbolmacro m-zero m-zero)
(defsymbolmacro m-plus m-plus)

(defmacro m-lift
  "Converts a function f of n arguments into a function of n
  monadic arguments returning a monadic value."
  [n f]
  (let [expr (take n (repeatedly #(gensym "x_")))
        vars (vec (take n (repeatedly #(gensym "mv_"))))
        steps (vec (interleave expr vars))]
    (list `fn vars (monad-expr steps (cons f expr)))))

(defmonadfn m-join
  "Converts a monadic value containing a monadic value into a 'simple'
   monadic value."
  [m]
  (m-bind m identity))

(defmonadfn m-fmap
  "Bind the monadic value m to the function returning (f x) for argument x"
  [f m]
  (m-bind m (fn [x] (m-result (f x)))))

(defmonadfn m-seq
  "'Executes' the monadic values in ms and returns a sequence of the
   basic values contained in them."
  [ms]
  (reduce (fn [q p]
            (m-bind p (fn [x]
                        (m-bind q (fn [y]
                                    (m-result (cons x y)))) )))
          (m-result '())
          (reverse ms)))

(defmonadfn m-map
  "'Executes' the sequence of monadic values resulting from mapping
   f onto the values xs. f must return a monadic value."
  [f xs]
  (m-seq (map f xs)))

(defmonadfn m-chain
  "Chains together monadic computation steps that are each functions
   of one parameter. Each step is called with the result of the previous
   step as its argument. (m-chain (step1 step2)) is equivalent to
   (fn [x] (domonad [r1 (step1 x) r2 (step2 r1)] r2))."
  [steps]
  (reduce (fn m-chain-link [chain-expr step]
            (fn [v] (m-bind (chain-expr v) step)))
          m-result
          steps))

(defmonadfn m-reduce
  "Return the reduction of (m-lift 2 f) over the list of monadic values mvs
   with initial value (m-result val)."
  ([f mvs]
   (if (empty? mvs)
     (m-result (f))
     (let [m-f (m-lift 2 f)]
       (reduce m-f mvs))))
  ([f val mvs]
   (let [m-f    (m-lift 2 f)
         m-val  (m-result val)]
     (reduce m-f m-val mvs))))

(defmonadfn m-until
  "While (p x) is false, replace x by the value returned by the
   monadic computation (f x). Return (m-result x) for the first
   x for which (p x) is true."
  [p f x]
  (if (p x)
    (m-result x)
    (domonad
      [y (f x)
       z (m-until p f y)]
      z)))

(defmacro m-when
  "If test is logical true, return monadic value m-expr, else return
   (m-result nil)."
  [test m-expr]
  `(if ~test ~m-expr (~'m-result nil)))

(defmacro m-when-not
  "If test if logical false, return monadic value m-expr, else return
   (m-result nil)."
  [test m-expr]
  `(if ~test (~'m-result nil) ~m-expr))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Utility functions used in monad definitions
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- flatten*
  "Like #(apply concat %), but fully lazy: it evaluates each sublist
   only when it is needed."
  [ss]
  (lazy-seq
   (when-let [s (seq ss)]
     (concat (first s) (flatten* (rest s))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Commonly used monads
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Identity monad
(defmonad identity-m
   "Monad describing plain computations. This monad does in fact nothing
    at all. It is useful for testing, for combination with monad
    transformers, and for code that is parameterized with a monad."
  [m-result identity
   m-bind   (fn m-result-id [mv f]
              (f mv))
  ])

; Maybe monad
(defmonad maybe-m
   "Monad describing computations with possible failures. Failure is
    represented by nil, any other value is considered valid. As soon as
    a step returns nil, the whole computation will yield nil as well."
   [m-zero   nil
    m-result (fn m-result-maybe [v] v)
    m-bind   (fn m-bind-maybe [mv f]
               (when-not (nil? mv) (f mv)))
    m-plus   (fn m-plus-maybe [& mvs]
               (first (drop-while nil? mvs)))
    ])

; Sequence monad (called "list monad" in Haskell)
(defmonad sequence-m
   "Monad describing multi-valued computations, i.e. computations
    that can yield multiple values. Any object implementing the seq
    protocol can be used as a monadic value."
   [m-result (fn m-result-sequence [v]
               (list v))
    m-bind   (fn m-bind-sequence [mv f]
               (flatten* (map f mv)))
    m-zero   (list)
    m-plus   (fn m-plus-sequence [& mvs]
               (flatten* mvs))
    ])

; Set monad
(defmonad set-m
   "Monad describing multi-valued computations, like sequence-m,
    but returning sets of results instead of sequences of results."
   [m-result (fn m-result-set [v]
               #{v})
    m-bind   (fn m-bind-set [mv f]
               (apply clojure.set/union (map f mv)))
    m-zero   #{}
    m-plus   (fn m-plus-set [& mvs]
               (apply clojure.set/union mvs))
    ])

; State monad
(defmonad state-m
   "Monad describing stateful computations. The monadic values have the
    structure (fn [old-state] [result new-state])."
   [m-result  (fn m-result-state [v]
                (fn [s] [v s]))
    m-bind    (fn m-bind-state [mv f]
                (fn [s]
                  (let [[v ss] (mv s)]
                    ((f v) ss))))
   ])

(defn update-state
  "Return a state-monad function that replaces the current state by the
   result of f applied to the current state and that returns the old state."
  [f]
  (fn [s] [s (f s)]))

(defn set-state
  "Return a state-monad function that replaces the current state by s and
   returns the previous state."
  [s]
  (update-state (fn [_] s)))

(defn fetch-state
  "Return a state-monad function that returns the current state and does not
   modify it."
  []
  (update-state identity))

(defn fetch-val
  "Return a state-monad function that assumes the state to be a map and
   returns the value corresponding to the given key. The state is not modified."
  [key]
  (fn [s] [(get s key ) s]))

(defn update-val
  "Return a state-monad function that assumes the state to be a map and
   replaces the value associated with the given key by the return value
   of f applied to the old value. The old value is returned."
  [key f]
  (fn [s]
    (let [old-val (get s key)
          new-s   (assoc s key (f old-val))]
      [old-val new-s])))

(defn set-val
  "Return a state-monad function that assumes the state to be a map and
   replaces the value associated with key by val. The old value is returned."
  [key val]
  (update-val key (fn [_] val)))

(defn with-state-field
  "Returns a state-monad function that expects a map as its state and
   runs statement (another state-monad function) on the state defined by
   the map entry corresponding to key. The map entry is updated with the
   new state returned by statement."
  [key statement]
  (fn [s]
    (let [substate (get s key nil)
          [result new-substate] (statement substate)
          new-state (assoc s key new-substate)]
      [result new-state])))

(defn state-m-until
  "An optimized implementation of m-until for the state monad that
   replaces recursion by a loop."
  [p f x]
  (letfn [(until [p f x s]
            (if (p x)
              [x s]
              (let [[x s] ((f x) s)]
                (recur p f x s))))]
    (fn [s] (until p f x s))))

; Writer monad
(defprotocol writer-monad-protocol
  "Accumulation of values into containers"
  (writer-m-add [container value]
  "add value to container, return new container")
  (writer-m-combine [container1 container2]
  "combine two containers, return new container"))

(extend-protocol writer-monad-protocol

  clojure.lang.IPersistentVector
  (writer-m-add [c v] (conj c v))
  (writer-m-combine [c1 c2] (vec (concat c1 c2)))

  clojure.lang.IPersistentList
  (writer-m-add [c v] (conj c v))
  (writer-m-combine [c1 c2] (apply list (concat c1 c2)))

  clojure.lang.APersistentSet
  (writer-m-add [c v] (conj c v))
  (writer-m-combine [c1 c2] (clojure.set/union c1 c2))

  java.lang.String
  (writer-m-add [c v] (str c v))
  (writer-m-combine [c1 c2] (str c1 c2)))

(defn writer-m
  "Monad describing computations that accumulate data on the side, e.g. for
   logging. The monadic values have the structure [value log]. Any of the
   accumulators from clojure.contrib.accumulators can be used for storing the
   log data. Its empty value is passed as a parameter."
  [empty-accumulator]
  (monad
   [m-result  (fn m-result-writer [v]
                [v empty-accumulator])
    m-bind    (fn m-bind-writer [mv f]
                (let [[v1 a1] mv
                      [v2 a2] (f v1)]
                  [v2 (writer-m-combine a1 a2)]))
    ]))

(defmonadfn write [v]
  (let [[_ a] (m-result nil)]
    [nil (writer-m-add a v)]))

(defn listen [mv]
  (let [[v a] mv] [[v a] a]))

(defn censor [f mv]
  (let [[v a] mv] [v (f a)]))

; Reader monad
(defmonad reader-m
  "Monad describing computations which read values from a shared environment.
  Also known as the environment monad."
  [m-result  (fn m-result-reader [v]
               (fn [r] v))
   m-bind    (fn m-bind-reader [mv f]
               (fn [r]
                 ((f (mv r)) r)))
   ])

(defn ask
  "Returns the environment."
  []
  identity)

(defn asks
  "Returns a function of the current environment."
  [f]
  (fn [env]
    (f env)))

(defn local
  "Runs reader g in the context of an environment modified by f"
  [f g]
  (fn [env]
    (g (f env))))

; Continuation monad
(defmonad cont-m
  "Monad describing computations in continuation-passing style. The monadic
   values are functions that are called with a single argument representing
   the continuation of the computation, to which they pass their result."
  [m-result   (fn m-result-cont [v]
                (fn [c] (c v)))
   m-bind     (fn m-bind-cont [mv f]
                (fn [c]
                  (mv (fn [v] ((f v) c)))))
   ])

(defn run-cont
  "Execute the computation c in the cont monad and return its result."
  [c]
  (c identity))

(defn call-cc
  "A computation in the cont monad that calls function f with a single
   argument representing the current continuation. The function f should
   return a continuation (which becomes the return value of call-cc),
   or call the passed-in current continuation to terminate."
  [f]
  (fn [c]
    (let [cc (fn cc [a] (fn [_] (c a)))
          rc (f cc)]
      (rc c))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Monad transformers
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmacro monad-transformer
  "Define a monad transformer in terms of the monad operations and the base
   monad. The argument which-m-plus chooses if m-zero and m-plus are taken
   from the base monad or from the transformer."
  [base which-m-plus operations]
  `(let [which-m-plus# (cond (= ~which-m-plus :m-plus-default)
                             (if (= ::this-monad-does-not-define-m-plus
                                    (with-monad ~base ~'m-plus))
                                 :m-plus-from-transformer
                                 :m-plus-from-base)
                             (or (= ~which-m-plus :m-plus-from-base)
                                 (= ~which-m-plus :m-plus-from-transformer))
                               ~which-m-plus
                             :else
                               (throw (java.lang.IllegalArgumentException.
                                       "undefined m-plus choice")))
         combined-monad# (monad ~operations)]
    (if (= which-m-plus# :m-plus-from-base)
      (assoc combined-monad#
        :m-zero (with-monad ~base ~'m-zero)
        :m-plus (with-monad ~base ~'m-plus))
      combined-monad#)))

(defn maybe-t
  "Monad transformer that transforms a monad m into a monad in which
   the base values can be invalid (represented by nothing, which defaults
   to nil). The third argument chooses if m-zero and m-plus are inherited
   from the base monad (use :m-plus-from-base) or adopt maybe-like
   behaviour (use :m-plus-from-transformer). The default is :m-plus-from-base
   if the base monad m has a definition for m-plus, and
   :m-plus-from-transformer otherwise."
  ([m] (maybe-t m nil :m-plus-default))
  ([m nothing] (maybe-t m nothing :m-plus-default))
  ([m nothing which-m-plus]
   (monad-transformer m which-m-plus
     [m-result (with-monad m m-result)
      m-bind   (with-monad m
                 (fn m-bind-maybe-t [mv f]
                   (m-bind mv
                           (fn [x]
                             (if (identical? x nothing)
                               (m-result nothing)
                               (f x))))))
      m-zero   (with-monad m (m-result nothing))
      m-plus   (with-monad m
                 (fn m-plus-maybe-t [& mvs]
                   (if (empty? mvs)
                     (m-result nothing)
                     (m-bind (first mvs)
                             (fn [v]
                               (if (= v nothing)
                                 (apply m-plus-maybe-t (rest mvs))
                                 (m-result v)))))))
      ])))

(defn sequence-t
  "Monad transformer that transforms a monad m into a monad in which
   the base values are sequences. The argument which-m-plus chooses
   if m-zero and m-plus are inherited from the base monad
   (use :m-plus-from-base) or adopt sequence-like
   behaviour (use :m-plus-from-transformer). The default is :m-plus-from-base
   if the base monad m has a definition for m-plus, and
   :m-plus-from-transformer otherwise."
  ([m] (sequence-t m :m-plus-default))
  ([m which-m-plus]
   (monad-transformer m which-m-plus
     [m-result (with-monad m
                 (fn m-result-sequence-t [v]
                   (m-result (list v))))
      m-bind   (with-monad m
                 (fn m-bind-sequence-t [mv f]
                   (m-bind mv
                           (fn [xs]
                             (m-fmap flatten*
                                     (m-map f xs))))))
      m-zero   (with-monad m (m-result (list)))
      m-plus   (with-monad m
                 (fn m-plus-sequence-t [& mvs]
                   (m-reduce concat (list) mvs)))
      ])))

;; Contributed by Jim Duey
(defn state-t
  "Monad transformer that transforms a monad m into a monad of stateful
  computations that have the base monad type as their result."
  [m]
  (monad [m-result (with-monad m
                     (fn m-result-state-t [v]
                       (fn [s]
                         (m-result [v s]))))
          m-bind   (with-monad m
                     (fn m-bind-state-t [stm f]
                       (fn [s]
                         (m-bind (stm s)
                                 (fn [[v ss]]
                                   ((f v) ss))))))
          m-zero   (with-monad m
                     (if (= ::this-monad-does-not-define-m-zero m-zero)
                       ::this-monad-does-not-define-m-zero
                       (fn [s]
                         m-zero)))
          m-plus   (with-monad m
                     (if (= ::this-monad-does-not-define-m-plus m-plus)
                       ::this-monad-does-not-define-m-plus
                       (fn [& stms]
                         (fn [s]
                           (apply m-plus (map #(% s) stms))))))
          ]))
