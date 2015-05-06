(ns btcj.tracker-comm)

(defn sha-1 [string]
  (apply str (map (partial format "%02x")
                  (.digest (doto (java.security.MessageDigest/getInstance "SHA-1")
                             (.update (.getBytes string "ISO-8859-1")))))))