(ns gilded.core
  (:require [clojure.string :as str]))

(defn make-store [items]
  (assert (vector? items))
  (->> items
       (map (fn [item] (atom item)))
       vec))

(defn item-seq [store]
  (->> store
       (map deref)))
;; ---


(defn legendary?
  "Checks if an item is named 'Sulfuras, Hand of Ragnaros', the only legendary item in the store."
  [item]
  (= (:name @item) "Sulfuras, Hand of Ragnaros"))

(defn concert?
  "Checks if an item is a concert."
  [item]
  (= (:name @item) "Backstage passes to a TAFKAL80ETC concert"))

(defn better-with-age?
  "Checks if an item is aged brie, the only item that gets better with age, atm."
  [item]
  (= (:name @item) "Aged Brie"))

;; write a fn that checks if an item is conjured
(defn conjured?
  "Checks if an item is conjured."
  [item]
  (str/starts-with? (:name @item) "Conjured"))

(defn update-quality! [store]
  ;; this function is the only one that should be modified, according to the instructions.
  (doseq [item store]
    (if (and (not (better-with-age? item))
             (not (concert? item)))

      (when (> (:quality @item) 0)
        (when (not (legendary? item))
          (swap! item update :quality dec)))

      (when (< (:quality @item) 50)
        (swap! item update :quality inc)
        (when (concert? item)
          (when (< (:sell-in @item) 11)
            (when (< (:quality @item) 50)
              (swap! item update :quality inc)))
          (when (< (:sell-in @item) 6)
            (when (< (:quality @item) 50)))
          (swap! item update :quality inc))))

    (when (not (legendary? item))
      (swap! item update :sell-in dec))

    (when (< (:sell-in @item) 0)
      (if (not (better-with-age? item))
        (if (not (concert? item))
          (when (> (:quality @item) 0)
            (when (not (legendary? item))
              (swap! item update :quality dec)))
          (swap! item update :quality #(- % %)))
        (when (< (:quality @item) 50)
          (swap! item update :quality inc))))))
