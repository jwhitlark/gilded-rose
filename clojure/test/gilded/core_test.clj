(ns gilded.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [gilded.core :as g]
            [gilded.main :as m]))

(def fixture
  [{:name "foo", :quality 20, :sell-in 10}])

(def fixture2
  [{:name "foo", :quality 20, :sell-in 10}
   {:name "foo2", :quality 10, :sell-in 20}])

;; this fixture contains only sulfras and Elixir of the Mongoose
(def fixture3
  [{:name "Sulfuras, Hand of Ragnaros", :quality 80, :sell-in 0}
   {:name "Sulfuras, Hand of Ragnaros", :quality 80, :sell-in -1}
   {:name "Elixir of the Mongoose", :quality 7, :sell-in 5}])

(defn item->tuple [item]
  ((juxt :name :quality :sell-in) item))

(deftest simple-test
  (let [store-0 (g/make-store fixture)
        item-0 (first (g/item-seq store-0))
        store (g/make-store fixture)
        _ (g/update-quality! store)
        item (first (g/item-seq store))]
    (testing "day 0"
      (is (= "foo" (:name item-0)))
      (is (= 20 (:quality item-0)))
      (is (= 10 (:sell-in item-0)))
      (is (= ["foo" 20 10] (item->tuple item-0)))
     ;; end day 0
      )

    (testing "day 1"
      (is (= "foo" (:name item)))
      (is (= 19 (:quality item)))
      (is (= 9 (:sell-in item)))))
  ;; end day 1
  )

;; copilot generated this test
;; this test is similar to simple-test, but checks the whole store instead of each individual item.
(deftest simple-test2
  (let [store-0 (g/make-store fixture)
        store (g/make-store fixture)
        _ (g/update-quality! store)]
    (testing "day 0"
      (is (= [["foo" 20 10]] (map item->tuple (g/item-seq store-0)))))
    (testing "day 1"
      (is (= [["foo" 19 9]] (map item->tuple (g/item-seq store)))))))

;; copilot generated this test
;; this test is similar to simple-test2, but starts with fixture2 instead of fixture.
(deftest simple-test3
  (let [store-0 (g/make-store fixture2)
        store (g/make-store fixture2)
        _ (g/update-quality! store)]
    (testing "day 0"
      (is (= [["foo" 20 10] ["foo2" 10 20]] (map item->tuple (g/item-seq store-0)))))
    (testing "day 1"
      (is (= [["foo" 19 9] ["foo2" 9 19]] (map item->tuple (g/item-seq store)))))))

;; copilot generated this test
;; this test is similar to simple-test3, but uses fixture3 instead of fixture2.
(deftest simple-test4
  (let [store-0 (g/make-store fixture3)
        store (g/make-store fixture3)
        _ (g/update-quality! store)]
    (testing "day 0"
      (is (= [["Sulfuras, Hand of Ragnaros" 80 0]
              ["Sulfuras, Hand of Ragnaros" 80 -1]
              ["Elixir of the Mongoose" 7 5]]
             (map item->tuple (g/item-seq store-0)))))
    (testing "day 1"
      (is (= [["Sulfuras, Hand of Ragnaros" 80 0]
              ["Sulfuras, Hand of Ragnaros" 80 -1]
              ["Elixir of the Mongoose" 6 4]]
             (map item->tuple (g/item-seq store)))))))

;; copilot generated this test
;; this test checks that sulfras quality nor sell-in is updated.
(deftest sulfras-test
  (let [store-0 (g/make-store fixture3)
        store (g/make-store fixture3)
        _ (g/update-quality! store)]
    (testing "day 0"
      (is (= [["Sulfuras, Hand of Ragnaros" 80 0]
              ["Sulfuras, Hand of Ragnaros" 80 -1]
              ["Elixir of the Mongoose" 7 5]]
             (map item->tuple (g/item-seq store-0)))))
    (testing "day 1"
      (is (= [["Sulfuras, Hand of Ragnaros" 80 0]
              ["Sulfuras, Hand of Ragnaros" 80 -1]
              ["Elixir of the Mongoose" 6 4]]
             (map item->tuple (g/item-seq store)))))))

;; copilot generated this test, but I modified it's setup
;; this test is similar to simple-test2, but checks from day 0 to day 10.
(deftest simple-test5
  (let [store (g/make-store fixture)]
    (testing "day 0"
      (is (= [["foo" 20 10]] (map item->tuple (g/item-seq store)))))
    (testing "day 1"
      (g/update-quality! store)
      (is (= [["foo" 19 9]] (map item->tuple (g/item-seq store)))))
    (testing "day 2"
      (g/update-quality! store)
      (is (= [["foo" 18 8]] (map item->tuple (g/item-seq store)))))
    (testing "day 3"
      (g/update-quality! store)
      (is (= [["foo" 17 7]] (map item->tuple (g/item-seq store)))))
    (testing "day 4"
      (g/update-quality! store)
      (is (= [["foo" 16 6]] (map item->tuple (g/item-seq store)))))
    (testing "day 5"
      (g/update-quality! store)
      (is (= [["foo" 15 5]] (map item->tuple (g/item-seq store)))))
    (testing "day 6"
      (g/update-quality! store)
      (is (= [["foo" 14 4]] (map item->tuple (g/item-seq store)))))
    (testing "day 7"
      (g/update-quality! store)
      (is (= [["foo" 13 3]] (map item->tuple (g/item-seq store)))))
    (testing "day 8"
      (g/update-quality! store)
      (is (= [["foo" 12 2]] (map item->tuple (g/item-seq store)))))
    (testing "day 9"
      (g/update-quality! store)
      (is (= [["foo" 11 1]] (map item->tuple (g/item-seq store)))))
    (testing "day 10"
      (g/update-quality! store)
      (is (= [["foo" 10 0]] (map item->tuple (g/item-seq store)))))
    (testing "day 11, double decline"
      (g/update-quality! store)
      (is (= [["foo" 8 -1]] (map item->tuple (g/item-seq store)))))
    (testing "day 12, double decline"
      (g/update-quality! store)
      (is (= [["foo" 6 -2]] (map item->tuple (g/item-seq store)))))
    ;; end simple-test5
    ))

;; write a test that creates it's own fixture, with a conjured item, and then tests g/conjured?
(deftest ^:kaocha/pending conjured-test
  (let [store (g/make-store [{:name "Conjured Mana Cake", :quality 6, :sell-in 3}])]
    (testing "day 0"
      (is (= [["Conjured Mana Cake" 6 3]] (map item->tuple (g/item-seq store)))))
    (testing "day 1"
      (g/update-quality! store)
      (is (= [["Conjured Mana Cake" 4 2]] (map item->tuple (g/item-seq store)))))
    (testing "day 2"
      (g/update-quality! store)
      (is (= [["Conjured Mana Cake" 2 1]] (map item->tuple (g/item-seq store)))))
    (testing "day 3"
      (g/update-quality! store)
      (is (= [["Conjured Mana Cake" 0 0]] (map item->tuple (g/item-seq store)))))
    (testing "day 4"
      (g/update-quality! store)
      (is (= [["Conjured Mana Cake" 0 -1]] (map item->tuple (g/item-seq store)))))
    ;; end conjured-test
    ))

;; what is the constant for the max positive integer in clojure?
;; I'm using Long/MAX_VALUE
