(ns en.477
  "Box of chocolates

   You work at a chocolate shop that makes two sizes of chocolates:
   - Small (2 grams each)
   - Large (5 grams each)

   When someone orders a box of chocolates, they order by total mass. It's your
   job to figure out how to fulfill the order using a combination of small and
   large chocolates to exactly hit the total mass ordered.

   Your task is to write a function that takes three arguments:
   - smalls - The number of small chocolates available in the inventory.
   - larges - The number of large chocolates available in the inventory.
   - mass - The total mass of the ordered box.

   Your function should return a map containing:
   {:small ;; the number of small chocolates
    :large ;; the number of large chocolates
   }

   Or nil if the total mass is not possible.

   One other constraint is you should strive to have the fewest number of
   chocolates that total to the target mass. That means you should prefer large
   chocolates over small chocolates if you have the choice.")

(def small-weight 2)
(def large-weight 5)


(defn- calculate-small
  [large mass]
  (quot (max 0 (- mass (* large large-weight))) small-weight))

(defn- filled?
  [small large mass]
  (= (+ (* small small-weight)
        (* large large-weight))
     mass))

(defn assemble
  [small-inventory large-inventory mass]
  (loop [large (quot mass large-weight)
         small (calculate-small large mass)]
    (cond
      (> large large-inventory) (recur (dec large)
                                       (calculate-small (dec large) mass))
      (> small small-inventory) nil
      (filled? small large mass) {:small small :large large}
      (zero? large) nil
      :else (recur (dec large)
                   (calculate-small (dec large) mass)))))
