(ns advent-of-code.2023.03
  (:require
   [advent-of-code.2023.io :refer [read-input!]]
   [clojure.string :as str]))

(defn- ->left-sides
  [[x y]]
  [[(dec x) y]
   [(dec x) (dec y)]
   [(dec x) (inc y)]])

(defn- ->right-sides
  [[x y]]
  [[(inc x) y]
   [(inc x) (dec y)]
   [(inc x) (inc y)]])

(defn- ->top-bottom-sides
  [[x y]]
  [[x (dec y)]
   [x (inc y)]])

(defn- ->sides
  [coords]
  (let [start (first coords)
        end (last coords)
        coords (-> coords rest butlast)]
    (->> (concat (->left-sides start)
                 (->top-bottom-sides start)
                 (->right-sides end)
                 (->top-bottom-sides end)
                 (mapcat ->top-bottom-sides coords))
         distinct)))

(defn- locate
  [n s]
  (let [pattern (re-pattern "(\\d+|[^0-9.])")
        matcher (.matcher pattern s)]
    (loop [acc []]
      (if-not (.find matcher)
        acc
        (let [match (.group matcher)
              type (if (re-matches #"\d+" match) :number :symbol)
              match (if (= type :number)
                      (Integer/parseInt match)
                      match)
              coords (map #(vector % n)
                          (range (.start matcher) (.end matcher)))]
          (recur
           (conj acc {:match match
                      :type type
                      :coords coords
                      :sides (->sides coords)})))))))

(defn- part-number?
  [sym-coords {:keys [sides]}]
  (some #(contains? sym-coords %) sides))

(defn- solve!-p1
  []
  (let [schema-anal (-> (read-input! "03")
                        (str/split #"\n")
                        (->> (mapcat locate (range))))
        sym-coords (->> schema-anal
                        (filter #(= (:type %) :symbol))
                        (map :coords)
                        (map first)
                        (into #{}))]
    (->> schema-anal
         (filter #(= (:type %) :number))
         (filter #(part-number? sym-coords %))
         (map :match)
         (apply +))))

(defn- ->adjacent-numbers
  [coord->num {:keys [sides]}]
  (->> sides
       (map coord->num)
       (filter some?)
       distinct))

(defn- solve!-p2
  []
  (let [schema-anal (-> (read-input! "03")
                        (str/split #"\n")
                        (->> (mapcat locate (range))))
        coord->num (->> schema-anal
                        (filter #(= (:type %) :number))
                        (map (fn [{:keys [coords match]}]
                               (apply hash-map (interleave coords (repeat match)))))
                        (reduce merge))]
    (->> schema-anal
         (filter #(= (:type %) :symbol))
         (map #(->adjacent-numbers coord->num %))
         (filter #(= (count %) 2))
         (map #(apply * %))
         (apply +))))

(comment
  (solve!-p1)
  ;; => 550064

  (solve!-p2)
  ;; => 85010461
  )
