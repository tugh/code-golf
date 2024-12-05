(ns advent-of-code.2024.05
  (:require
   [advent-of-code.2024.io :as io]
   [clojure.math :as math]
   [clojure.string :as str]))

(defn- parse-rules
  [rules-raw]
  (->> rules-raw
       str/split-lines
       (reduce (fn [acc x]
                 (let [[a b] (str/split x #"\|")
                       a (Integer/parseInt a)
                       b (Integer/parseInt b)]
                   (-> acc
                       (update a #(if (nil? %)
                                    {:before #{b}
                                     :after #{}}
                                    (update % :before conj b)))
                       (update b #(if (nil? %)
                                    {:before #{}
                                     :after #{a}}
                                    (update % :after conj a)))))) {})))

(defn- parse-updates
  [updates-raw]
  (->> updates-raw
       str/split-lines
       (map #(->> (str/split % #",")
                  (map (fn [x] (Integer/parseInt x)))))))

(defn- read-input!
  []
  (let [[rules-raw updates-raw] (-> "05"
                                    io/read-input!
                                    (str/split #"\n\n"))
        rules (parse-rules rules-raw)
        updates (parse-updates updates-raw)]
    [rules updates]))

(defn- update-valid?
  [rules x y]
  (and (not (contains? (:after (get rules x)) y))
       (not (contains? (:before (get rules y)) x))))

(defn- update-sequence-valid?
  [rules update-sequence]
  (loop [[x & xs] update-sequence]
    (if (nil? x)
      true
      (if (every? #(update-valid? rules x %) xs)
        (recur xs)
        false))))

(defn- ->middle
  [x]
  (->> (count x)
       (#(math/floor-div % 2))
       (nth x)))

(defn- solve-p1
  [[rules updates]]
  (->> updates
       (filter #(update-sequence-valid? rules %))
       (map ->middle)
       (apply +)))

(comment
  (solve-p1 (read-input!))
  ;;=> 5087
  
  )