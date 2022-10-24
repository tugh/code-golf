(ns pftv.412 
  (:require
   [clojure.string :as str]
   [util :refer [at-least-two? capitalized? ends-with-dot? alphabetic? two?]]))

(def initial?
  (every-pred
   #(two? (count %))
   capitalized?
   ends-with-dot?))

(def word?
  (every-pred
   #(at-least-two? (count %))
   capitalized?
   alphabetic?))

(def valid-token?
  (some-fn
   initial?
   word?))

(defn- tokenize
  [s]
  (str/split s #" "))

(defn- ends-with-word?
  [ts]
  (-> ts last word?))

(defn valid-name?
  [s]
  ((every-pred
    #(at-least-two? (count %))
    ends-with-word?
    #(every? valid-token? %))
   (tokenize s)))
