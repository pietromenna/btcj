(ns btcj.metainfo_file
   (:require [btcj.bencoding :refer :all]))

(def info "info")

(def piece_length "piece length")

(def length "length")

(def announce "announce")

(def torrent_files [])

(def pieces [])

(defn metainfo_announce [file_contents]
  (file_contents announce))

(defn metainfo_files [file_contents] 
  (vector ((file_contents info) "name")))

(defn metainfo_info_length [] )

(defn metainfo_info_pieces_length [] )

(defn metainfo_well_formed [file_contents] )