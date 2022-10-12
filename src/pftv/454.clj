(ns pftv.454
  "Backspace jam
   
   Let's say we have users typing keys on the keyboard. We capture the
   characters they represent in strings. However, sometimes the user hits the
   backspace key, which removes the previous character. We will represent a
   backspace key press with a # character. Write a function that applies the
   behavior of backspace to the string.")

(defn apply-bs
  [s]
  (loop [c (first s)
         s (rest s)
         acc []]
    (if (nil? c)
      (apply str acc)
      (recur (first s)
             (rest s)
             (if (= c \#)
               (if-not (empty? acc)
                 (pop acc)
                 acc)
               (conj acc c))))))
