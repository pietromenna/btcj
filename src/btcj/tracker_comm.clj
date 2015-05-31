(ns btcj.tracker-comm
  (:require [ring.util.codec :refer :all]))

(defn sha-1 [string]
  (apply str (map (partial format "%02x")
                  (.digest (doto (java.security.MessageDigest/getInstance "SHA-1")
                             (.update (.getBytes string "ISO-8859-1")))))))

(defn- sha1-to-byte-array [string]
  (byte-array (take 20 (.toByteArray (BigInteger. string 16)))))

(defn sha1-to-form-encoded [string]
  (form-encode (new String (sha1-to-byte-array string) "ISO-8859-1") "ISO-8859-1"))