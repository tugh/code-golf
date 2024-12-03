(ns advent-of-code.2024.03
  (:require
   [advent-of-code.2024.io :as io]))

(defn solve-p1
  [input]
  (->> input
       (re-seq #"mul\((\d+)\,(\d+)\)")
       (map (fn [[_ x y]] (* (Integer/parseInt x) (Integer/parseInt y))))
       (apply +)))

(defn solve-p2
  [input]
  (let [exprs (re-seq #"do\(\)|don\'t\(\)|mul\((\d+)\,(\d+)\)" input)]
    (loop [[[expr x y] & exprs] exprs
           enabled? true
           acc 0]
      (if (nil? expr)
        acc
        (case expr
          "do()" (recur exprs true acc)
          "don't()" (recur exprs false acc)
          (if-not enabled?
            (recur exprs enabled? acc)
            (recur exprs enabled? (+ acc (* (Integer/parseInt x)
                                            (Integer/parseInt y))))))))))


(comment
  (-> (io/read-input! "03")
      solve-p1)
  ;;=> 157621318

  (-> (io/read-input! "03")
      solve-p2)
  ;;=> 79845780

  )
