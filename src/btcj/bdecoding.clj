(ns btcj.bdecoding
  (:require [clojure.java.io :as io]
            [btcj.bencoding :refer :all]))

(defn bdecode-stream [] )

(defn bdecode-dict [stream] 
  (hash-map))

(defn bdecode-list [stream] 
  (vector))

(defn bdecode-int [stream] 
  (let [first-ocurrence (apply str (re-seq #"\bi[0-9]+e" stream))
        decoded-integer (Integer. (apply str (drop 1 (drop-last 1 first-ocurrence))))]
      decoded-integer))

(defn bdecode-string [stream] 
  (let [descriptor-length
        (count (apply str(take-while #(not (= \: %)) stream)))
        length 
        (Integer. (apply str(take-while #(not (= \: %)) stream)))]
    (apply str (take length (apply str (drop (+ descriptor-length 1) stream))))))

(defn rest-stream [stream]
  (cond 
    (= 0 (count stream))
      nil
    (= (first stream) int-begin-delimiter)
    (let [first-int (apply str (re-seq #"\bi[0-9]+e" stream))] 
      (if (= (count first-int) (count stream))
        nil
        (apply str (drop (count first-int) stream))))
    (Character/isDigit (first stream))
    (let [descriptor-length
      (count (apply str(take-while #(not (= \: %)) stream)))
      length
      (Integer. (apply str(take-while #(not (= \: %)) stream)))
      total-length (+ 1 descriptor-length length)]
        (if (= (count stream) total-length)
          nil
          (apply str (drop total-length stream))))))

(defn bdecode-simple [stream]
  (cond 
    (empty? stream) nil 
    (= (first stream) dict-begin-delimiter) (bdecode-dict stream)
    (= (first stream) list-begin-delimiter) (bdecode-list stream)
    (= (first stream) int-begin-delimiter) (bdecode-int stream)
    (Character/isDigit (first stream)) (bdecode-string stream)))

(defn append [item1 item2]
  (if (= (coll? item1) (coll? item2) false)
         (list item2 item1)
         (conj item1 item2)))

(defn bdecode-stream [stream]
  (let [stream-rest (rest-stream stream)]
    (if (empty? stream-rest)
      (bdecode-simple stream)
      (append (bdecode-stream stream-rest) (bdecode-simple stream)))))


