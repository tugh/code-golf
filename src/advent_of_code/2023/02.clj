(ns advent-of-code.2023.02
  (:require
   [advent-of-code.2023.io :refer [read-input!]]
   [clojure.string :as str]))

(-> (read-input! "02")
    (str/split #"\n"))

(defn- ->game-id
  [s]
  (-> s
      (str/split #":")
      first
      (str/split #" ")
      second
      Integer/parseInt))

(defn- ->hand
  [s]
  (let [[n color] (str/split s #" ")]
    [(keyword color) (Integer/parseInt n)]))

(defn- ->set
  [s]
  (-> s
      (str/split #", ")
      (->> (map ->hand)
           (reduce concat)
           (apply hash-map))))

(defn- ->sets
  [s]
  (-> s
      (str/split #": ")
      second
      (str/split #"; ")
      (->> (map ->set))))

(defn- ->game
  [s]
  {:id (->game-id s)
   :sets (->sets s)})

(defn- possible?
  [{:keys [red green blue]} {:keys [sets]}]
  (every? true? (map #(and (>= red (or (:red %) 0))
                           (>= green (or (:green %) 0))
                           (>= blue (or (:blue %) 0)))
                     sets)))

(defn- solve!-p1
  []
  (let [cfg {:red 12 :green 13 :blue 14}]
    (-> (read-input! "02")
        (str/split #"\n")
        (->> (map ->game)
             (filter #(possible? cfg %))
             (map :id)
             (apply +)))))

(defn- ->min-cfg
  [{:keys [sets]}]
  (reduce (fn [acc {:keys [red green blue]
                    :or {red 0 green 0 blue 0}}]
            (cond-> acc
              (> red (:red acc)) (assoc :red red)
              (> green (:green acc)) (assoc :green green)
              (> blue (:blue acc)) (assoc :blue blue)))
          {:red 0 :green 0 :blue 0}
          sets))

(defn- ->power
  [{:keys [red green blue]}]
  (* red green blue))

(defn- solve!-p2
  []
  (-> (read-input! "02")
      (str/split #"\n")
      (->> (map ->game)
           (map ->min-cfg)
           (map ->power)
           (apply +))))

(comment
  (solve!-p1)
  ;; => 2593

  (solve!-p2)
  ;; => 54699
  )
