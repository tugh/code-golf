(ns advent-of-code.2023.01
  (:require
   [advent-of-code.2023.io :refer [read-input!]]
   [clojure.string :as str]))

(def ^:private spelled-digits
  ["zero" "one" "two" "three" "four" "five" "six" "seven" "eight" "nine"])

(def ^:private spelled-digit->int
  (apply hash-map (interleave spelled-digits (range 0 10))))

(defn- numeric-digit?
  [x]
  (Character/isDigit x))

(defn- numeric-chars->digits
  [s]
  (->> s
       (filter numeric-digit?)
       (map str)
       (map #(Integer/parseInt %))))

(defn- solve!-p1
  []
  (-> (read-input! "01")
      (str/split #"\n")
      (->> (map numeric-chars->digits)
           (map (juxt first last))
           (map #(+ (* 10 (first %)) (second %)))
           (apply +))))

(defn- starts-with-spelled-digit?
  [s]
  (let [f #(str/starts-with? %2 %1)
        fs (map #(partial f %) spelled-digits)]
    (boolean (some true? ((apply juxt fs) s)))))

(defn- ->starting-spelled-digit
  [s]
  (second
   (re-matches
    (re-pattern
     (str "^(" (str/join "|" spelled-digits) ").*"))
    s)))

(defn- ->numeric+spelled-digits
  [s]
  (loop [s s
         curr (first s)
         acc []]
    (cond
      (nil? curr)
      acc

      (numeric-digit? curr)
      (recur (subs s 1)
             (second s)
             (conj acc (Integer/parseInt (str curr))))
      
      (starts-with-spelled-digit? s)
      (let [spelled-digit (->starting-spelled-digit s)
            digit (spelled-digit->int spelled-digit)]
        (recur (subs s 1)
               (second s)
               (conj acc digit)))
      
      :else
      (recur (subs s 1)
             (second s)
             acc))))

(defn- solve!-p2
  []
  (-> (read-input! "01")
      (str/split #"\n")
      (->> (map ->numeric+spelled-digits)
           (map (juxt first last))
           (map #(+ (* 10 (first %)) (last %)))
           (apply +))))

(comment
  (solve!-p1)
  ;; => 54597

  (solve!-p2)
  ;; => 54504
  )