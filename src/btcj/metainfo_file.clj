(ns btcj.metainfo_file
   (:require [btcj.bencoding :refer :all]))

(def info "info")

(def piece_length "piece length")

(def length "length")

(def announce "announce")

(def pieces "pieces")

(defn metainfo_announce [file_contents]
  (file_contents announce))

(defn metainfo_files [file_contents] 
  (vector ((file_contents info) "name")))

(defn metainfo_info_length [file_contents]
  ((file_contents info) length))

(defn metainfo_info_pieces_length [file_contents]
   ((file_contents info) piece_length))

(defn metainfo_info_number_of_pieces [file_contents]
  (count ((file_contents info) pieces)))

; (defn metainfo_well_formed [file_contents] 
;   (if (= (/ (metainfo_info_length file_contents) (metainfo_info_number_of_pieces file_contents))
;             (/ (metainfo_info_number_of_pieces file_contents) 20))
;     true
;     false))