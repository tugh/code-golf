(ns en.478
  "Super Digit

   This is kind of a contrived problem, but it's the kind that breeds lots of
   interesting implementations and tests your understanding of lower-level
   details. So let's do it!

   You're given an integer n and an integer k. There is an integer p that is k
   instances of the digits of n concatenated together. For example:

   n=123, k=3 -> p=123123123
   n=32, k=6 -> p=323232323232
   n=24543, k=125 -> p=245432454324543245432454324543...

   Now, take that number p and find its superdigit. The superdigit is defined as
   follows:

   superdigit(d) = d                             if # of digits = 1
   superdigit(d) = superdigit(sum(digits of d))  otherwise

   That is, if the number has one digit, the superdigit is the number.
   (Example: superdigit(4)=4). Otherwise, sum the digits and take the superdigit
   of the result. (Example: superdigit(23)=superdigit(2+3)=5).

   Your task is to write a function that calculates the superdigit of n and k."
   (:require
    [util :as util]))

(defn superdigit
  ([n]
   (if (< n 10)
     n
     (superdigit (->> n
                      util/number->digits
                      (reduce +)))))
  ([n k]
   (superdigit (->> n
                    util/number->digits
                    (reduce +)
                    (* k)))))
