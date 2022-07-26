(ns en.471
  (:require
   [clojure.string :as str]))

(defn regroupv1
  [plate size]
  (let [plate (str/replace plate "-" "")
        length (count plate)
        remainder (rem length size)
        first-group (take remainder plate)]
    (loop [groups (if (empty? first-group)
                    []
                    [first-group])
           plate (drop remainder plate)]
      (if (empty? plate)
        (->> groups
             (map #(apply str %))
             (str/join "-"))
        (recur (conj groups (take size plate))
               (drop size plate))))))

(defn regroupv2
  [plate size]
  (let [reversed-codes (-> plate
                           (str/replace "-" "")
                           reverse)]
    (->> reversed-codes
         (partition-all size)
         reverse
         (map #(apply str (reverse %)))
         (str/join "-"))))
