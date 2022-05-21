(ns en.469)

(defn fib-seq
  ([]
   (fib-seq + 1 1))
  ([x y]
   (fib-seq + x y))
  ([f x y]
   (lazy-seq (cons x (fib-seq f y (f y x))))))
