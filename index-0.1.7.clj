{:namespaces
 ({:doc
   "This library contains the most commonly used monads as well\nas macros for defining and using monads and useful monadic\nfunctions.",
   :author "Konrad Hinsen",
   :name "clojure.algo.monads",
   :wiki-url "http://clojure.github.io/algo.monads/index.html",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj"}),
 :vars
 ({:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "ask",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L533",
   :line 533,
   :var-type "function",
   :arglists ([]),
   :doc "Returns the environment.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/ask"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "asks",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L538",
   :line 538,
   :var-type "function",
   :arglists ([f]),
   :doc "Returns a function of the current environment.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/asks"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "call-cc",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L567",
   :line 567,
   :var-type "function",
   :arglists ([f]),
   :doc
   "A computation in the cont monad that calls function f with a single\nargument representing the current continuation. The function f should\nreturn a continuation (which becomes the return value of call-cc),\nor call the passed-in current continuation to terminate.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/call-cc"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "cond-statement",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L108",
   :line 108,
   :var-type "function",
   :arglists ([expr mexpr continuation]),
   :doc
   "Process a :cond steps when adding a new monadic step to the mexpr.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/cond-statement"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "cont-m",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L551",
   :line 551,
   :var-type "var",
   :arglists nil,
   :doc
   "Monad describing computations in continuation-passing style. The monadic\nvalues are functions that are called with a single argument representing\nthe continuation of the computation, to which they pass their result.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/cont-m"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "defmonad",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L47",
   :line 47,
   :var-type "macro",
   :arglists ([name doc-string operations] [name operations]),
   :doc
   "Define a named monad by defining the monad operations. The definitions\nare written like bindings to the monad operations m-bind and\nm-result (required) and m-zero and m-plus (optional).",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/defmonad"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "defmonadfn",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L196",
   :line 196,
   :var-type "macro",
   :arglists
   ([name docstring? attr-map? args expr]
    [name docstring? attr-map? (args expr) ...]),
   :doc
   "Like defn, but for functions that use monad operations and are used inside\na with-monad block.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/defmonadfn"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "domonad",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L173",
   :line 173,
   :var-type "macro",
   :arglists ([steps expr] [name steps expr]),
   :doc
   "Monad comprehension. Takes the name of a monad, a vector of steps\ngiven as binding-form/monadic-expression pairs, and a result value\nspecified by expr. The monadic-expression terms can use the binding\nvariables of the previous steps.\nIf the monad contains a definition of m-zero, the step list can also\ncontain conditions of the form :when p, where the predicate p can\ncontain the binding variables from all previous steps.\nA clause of the form :let [binding-form expr ...], where the bindings\nare given as a vector as for the use in let, establishes additional\nbindings that can be used in the following steps.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/domonad"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "fetch-state",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L420",
   :line 420,
   :var-type "function",
   :arglists ([]),
   :doc
   "Return a state-monad function that returns the current state and does not\nmodify it.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/fetch-state"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "fetch-val",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L426",
   :line 426,
   :var-type "function",
   :arglists ([key]),
   :doc
   "Return a state-monad function that assumes the state to be a map and\nreturns the value corresponding to the given key. The state is not modified.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/fetch-val"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "identity-m",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L347",
   :line 347,
   :var-type "var",
   :arglists nil,
   :doc
   "Monad describing plain computations. This monad does in fact nothing\nat all. It is useful for testing, for combination with monad\ntransformers, and for code that is parameterized with a monad.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/identity-m"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "local",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L544",
   :line 544,
   :var-type "function",
   :arglists ([f g]),
   :doc "Runs reader g in the context of an environment modified by f",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/local"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "m-chain",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L278",
   :line 278,
   :var-type "var",
   :arglists nil,
   :doc
   "Chains together monadic computation steps that are each functions\nof one parameter. Each step is called with the result of the previous\nstep as its argument. (m-chain (step1 step2)) is equivalent to\n(fn [x] (domonad [r1 (step1 x) r2 (step2 r1)] r2)).",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/m-chain"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "m-fmap",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L256",
   :line 256,
   :var-type "var",
   :arglists nil,
   :doc
   "Bind the monadic value m to the function returning (f x) for argument x",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/m-fmap"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "m-join",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L250",
   :line 250,
   :var-type "var",
   :arglists nil,
   :doc
   "Converts a monadic value containing a monadic value into a 'simple'\nmonadic value.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/m-join"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "m-lift",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L241",
   :line 241,
   :var-type "macro",
   :arglists ([n f]),
   :doc
   "Converts a function f of n arguments into a function of n\nmonadic arguments returning a monadic value.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/m-lift"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "m-map",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L272",
   :line 272,
   :var-type "var",
   :arglists nil,
   :doc
   "'Executes' the sequence of monadic values resulting from mapping\nf onto the values xs. f must return a monadic value.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/m-map"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "m-reduce",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L289",
   :line 289,
   :var-type "var",
   :arglists nil,
   :doc
   "Return the reduction of (m-lift 2 f) over the list of monadic values mvs\nwith initial value (m-result val).",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/m-reduce"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "m-seq",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L261",
   :line 261,
   :var-type "var",
   :arglists nil,
   :doc
   "'Executes' the monadic values in ms and returns a sequence of the\nbasic values contained in them.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/m-seq"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "m-until",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L302",
   :line 302,
   :var-type "var",
   :arglists nil,
   :doc
   "While (p x) is false, replace x by the value returned by the\nmonadic computation (f x). Return (m-result x) for the first\nx for which (p x) is true.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/m-until"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "m-when",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L314",
   :line 314,
   :var-type "macro",
   :arglists ([test m-expr]),
   :doc
   "If test is logical true, return monadic value m-expr, else return\n(m-result nil).",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/m-when"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "m-when-not",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L320",
   :line 320,
   :var-type "macro",
   :arglists ([test m-expr]),
   :doc
   "If test if logical false, return monadic value m-expr, else return\n(m-result nil).",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/m-when-not"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "maybe-m",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L357",
   :line 357,
   :var-type "var",
   :arglists nil,
   :doc
   "Monad describing computations with possible failures. Failure is\nrepresented by nil, any other value is considered valid. As soon as\na step returns nil, the whole computation will yield nil as well.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/maybe-m"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "maybe-t",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L608",
   :line 608,
   :var-type "function",
   :arglists ([m] [m nothing] [m nothing which-m-plus]),
   :doc
   "Monad transformer that transforms a monad m into a monad in which\nthe base values can be invalid (represented by nothing, which defaults\nto nil). The third argument chooses if m-zero and m-plus are inherited\nfrom the base monad (use :m-plus-from-base) or adopt maybe-like\nbehaviour (use :m-plus-from-transformer). The default is :m-plus-from-base\nif the base monad m has a definition for m-plus, and\n:m-plus-from-transformer otherwise.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/maybe-t"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "monad",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L32",
   :line 32,
   :var-type "macro",
   :arglists ([operations]),
   :doc
   "Define a monad by defining the monad operations. The definitions\nare written like bindings to the monad operations m-bind and\nm-result (required) and m-zero and m-plus (optional).",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/monad"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "monad-transformer",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L585",
   :line 585,
   :var-type "macro",
   :arglists ([base which-m-plus operations]),
   :doc
   "Define a monad transformer in terms of the monad operations and the base\nmonad. The argument which-m-plus chooses if m-zero and m-plus are taken\nfrom the base monad or from the transformer.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/monad-transformer"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "reader-m",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L523",
   :line 523,
   :var-type "var",
   :arglists nil,
   :doc
   "Monad describing computations which read values from a shared environment.\nAlso known as the environment monad.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/reader-m"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "run-cont",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L562",
   :line 562,
   :var-type "function",
   :arglists ([c]),
   :doc
   "Execute the computation c in the cont monad and return its result.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/run-cont"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "sequence-m",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L370",
   :line 370,
   :var-type "var",
   :arglists nil,
   :doc
   "Monad describing multi-valued computations, i.e. computations\nthat can yield multiple values. Any object implementing the seq\nprotocol can be used as a monadic value.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/sequence-m"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "sequence-t",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L640",
   :line 640,
   :var-type "function",
   :arglists ([m] [m which-m-plus]),
   :doc
   "Monad transformer that transforms a monad m into a monad in which\nthe base values are sequences. The argument which-m-plus chooses\nif m-zero and m-plus are inherited from the base monad\n(use :m-plus-from-base) or adopt sequence-like\nbehaviour (use :m-plus-from-transformer). The default is :m-plus-from-base\nif the base monad m has a definition for m-plus, and\n:m-plus-from-transformer otherwise.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/sequence-t"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "set-m",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L384",
   :line 384,
   :var-type "var",
   :arglists nil,
   :doc
   "Monad describing multi-valued computations, like sequence-m,\nbut returning sets of results instead of sequences of results.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/set-m"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "set-state",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L414",
   :line 414,
   :var-type "function",
   :arglists ([s]),
   :doc
   "Return a state-monad function that replaces the current state by s and\nreturns the previous state.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/set-state"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "set-val",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L442",
   :line 442,
   :var-type "function",
   :arglists ([key val]),
   :doc
   "Return a state-monad function that assumes the state to be a map and\nreplaces the value associated with key by val. The old value is returned.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/set-val"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "state-m",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L397",
   :line 397,
   :var-type "var",
   :arglists nil,
   :doc
   "Monad describing stateful computations. The monadic values have the\nstructure (fn [old-state] [result new-state]).",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/state-m"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "state-m-until",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L460",
   :line 460,
   :var-type "function",
   :arglists ([p f x]),
   :doc
   "An optimized implementation of m-until for the state monad that\nreplaces recursion by a loop.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/state-m-until"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "state-t",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L667",
   :line 667,
   :var-type "function",
   :arglists ([m]),
   :doc
   "Monad transformer that transforms a monad m into a monad of stateful\ncomputations that have the base monad type as their result.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/state-t"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "update-state",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L408",
   :line 408,
   :var-type "function",
   :arglists ([f]),
   :doc
   "Return a state-monad function that replaces the current state by the\nresult of f applied to the current state and that returns the old state.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/update-state"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "update-val",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L432",
   :line 432,
   :var-type "function",
   :arglists ([key f]),
   :doc
   "Return a state-monad function that assumes the state to be a map and\nreplaces the value associated with the given key by the return value\nof f applied to the old value. The old value is returned.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/update-val"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "with-monad",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L161",
   :line 161,
   :var-type "macro",
   :arglists ([monad & exprs]),
   :doc
   "Evaluates an expression after replacing the keywords defining the\nmonad operations by the functions associated with these keywords\nin the monad definition given by name.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/with-monad"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "with-state-field",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L448",
   :line 448,
   :var-type "function",
   :arglists ([key statement]),
   :doc
   "Returns a state-monad function that expects a map as its state and\nruns statement (another state-monad function) on the state defined by\nthe map entry corresponding to key. The map entry is updated with the\nnew state returned by statement.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/with-state-field"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "writer-m",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L497",
   :line 497,
   :var-type "function",
   :arglists ([empty-accumulator]),
   :doc
   "Monad describing computations that accumulate data on the side, e.g. for\nlogging. The monadic values have the structure [value log]. Any of the\naccumulators from clojure.contrib.accumulators can be used for storing the\nlog data. Its empty value is passed as a parameter.",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/writer-m"}
  {:raw-source-url
   "https://github.com/clojure/algo.monads/raw/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj",
   :name "writer-monad-protocol",
   :file "src/main/clojure/clojure/algo/monads.clj",
   :source-url
   "https://github.com/clojure/algo.monads/blob/10a8af5a63ba1004fd0306cbebca4aede9ffc645/src/main/clojure/clojure/algo/monads.clj#L472",
   :line 472,
   :var-type "protocol",
   :arglists nil,
   :doc "Accumulation of values into containers",
   :namespace "clojure.algo.monads",
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/writer-monad-protocol"}
  {:name "writer-m-add",
   :doc "add value to container, return new container",
   :var-type "function",
   :namespace "clojure.algo.monads",
   :arglists ([container value]),
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/writer-m-add",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "writer-m-combine",
   :doc "combine two containers, return new container",
   :var-type "function",
   :namespace "clojure.algo.monads",
   :arglists ([container1 container2]),
   :wiki-url
   "http://clojure.github.io/algo.monads//index.html#clojure.algo.monads/writer-m-combine",
   :source-url nil,
   :raw-source-url nil,
   :file nil})}
