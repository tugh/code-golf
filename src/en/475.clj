(ns en.475
  "Least common multiple

   Write a function that finds the least common multiple of a collection of
   numbers. Remember that the least common multiple is the smallest integer that
   is evenly divisible by all the numbers in the collection.")

(defn -gcd
  [x y]
  (loop [x x
         y y]
    (if (zero? (mod x y))
      y
      (recur y (mod x y)))))

(defn -lcm
  [x y]
  (/ (* x y)
     (-gcd x y)))

(defn lcm
  [xs]
  (let [[x y & _] xs]
    (cond
      (nil? x) nil
      (nil? y) x
      :else    (reduce -lcm xs))))
