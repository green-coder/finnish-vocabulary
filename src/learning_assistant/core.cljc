(ns learning-assistant.core
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def vocabulary
  (->> (io/resource "duolingo-finnish.edn")
       slurp
       edn/read-string))

(def words
  (-> (for [[unit-id lessons] vocabulary
            {lesson-name :name
             words       :words} lessons
            word words]
        (-> word
            (assoc :unit unit-id
                   :lesson lesson-name)))
      vec))
