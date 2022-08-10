(ns en.473
  "Simplifying fractions

   A harder one for this week.

   Fractions are often represented in simplified form, where the numerator and
   denominator share only the factor 1. Write a function simplify that takes
   two integers (representing the numerator and denominator) and simplifies the
   fraction they represent, returning the two numbers.")

(defn simplify
  [x y]
  (try
    (->> (/ x y)
         ((juxt numerator denominator)))
    (catch ClassCastException _
      [(/ x y) 1])))
