(ns btcj.bencoding-test
  (:require [midje.sweet :refer :all]
            [btcj.bencoding :refer :all]))

; B "enconding" and B "decoding" tests shall be here
; All the tests here are based in the unofficial BitTorrentSpecification. 
; Source: https://wiki.theory.org/BitTorrentSpecification
; =======

; Bencoding is a way to specify and organize data in a terse format. It supports the following types: byte strings, integers, lists, and dictionaries.

; Bencoded Strings
; Bencoded strings are encoded as follows: <string length encoded in base ten ASCII>:<string data>, or key:value
; Note that there is no constant beginning delimiter, and no ending delimiter.

; Example: 4:spam represents the string "spam" 
; Example: 0: represents the empty string ""

(fact (bdecode-string "4:spam") => "spam" )

(fact (bdecode-string "0:") => "" )

(fact (bencode-string "") => "0:" )

(fact (bencode-string "spam") => "4:spam" )

; Integers
; Integers are encoded as follows: i<integer encoded in base ten ASCII>e
; The initial i and trailing e are beginning and ending delimiters. You can have negative numbers such as i-3e. Only the significant digits should be used, one cannot pad the Integer with zeroes. such as i04e. However, i0e is valid.
; Example: i3e represents the integer "3"
; NOTE: The maximum number of bit of this integer is unspecified, but to handle it as a signed 64bit integer is mandatory to handle "large files" aka .torrent for more that 4Gbyte.

(fact (bencode-int 3) => "i3e")

(fact (bdecode-int "i3e") => 3 )

; Lists
; Lists are encoded as follows: l<bencoded values>e
; The initial l and trailing e are beginning and ending delimiters. Lists may contain any bencoded type, including integers, strings, dictionaries, and even lists within other lists.
; Example: l4:spam4:eggse represents the list of two strings: [ "spam", "eggs" ] 
; Example: le represents an empty list: []

(fact (bencode-list []) => "le")

(fact (bdecode-list "le") => [] )

(fact (bencode-list []) => "le")

(fact (bdecode-list "l4:spam4:eggse") => [ "spam", "eggs" ]  )

; Dictionaries
; Dictionaries are encoded as follows: d<bencoded string><bencoded element>e 
; The initial d and trailing e are the beginning and ending delimiters. Note that the keys must be bencoded strings. The values may be any bencoded type, including integers, strings, lists, and other dictionaries. Keys must be strings and appear in sorted order (sorted as raw strings, not alphanumerics). The strings should be compared using a binary comparison, not a culture-specific "natural" comparison.
; Example: d3:cow3:moo4:spam4:eggse represents the dictionary { "cow" => "moo", "spam" => "eggs" } 
; Example: d4:spaml1:a1:bee' represents the dictionary { "spam" => [ "a", "b" ] } 
; Example: d9:publisher3:bob17:publisher-webpage15:www.example.com18:publisher.location4:homee represents { "publisher" => "bob", "publisher-webpage" => "www.example.com", "publisher.location" => "home" } 
; Example: de represents an empty dictionary {}

(fact (bencode-dict {} ) => "de")

(fact (bencode-dict {"cow" "moo", "spam" "eggs"} ) => "d3:cow3:moo4:spam4:eggse" )

(fact (bencode-dict {"spam" ["a", "b"]} ) => "d4:spaml1:a1:bee" )

(fact (bencode-dict { "publisher" "bob", "publisher-webpage" "www.example.com", "publisher.location" "home" } ) => "d9:publisher3:bob17:publisher-webpage15:www.example.com18:publisher.location4:homee")

(fact (bdecode-dict "de") => {} )

(fact (bdecode-dict "d3:cow3:moo4:spam4:eggse") => {"cow" "moo", "spam" "eggs"} )

(fact (bdecode-dict "d4:spaml1:a1:bee") => {"spam" ["a", "b"]} )

(fact (bdecode-dict "d9:publisher3:bob17:publisher-webpage15:www.example.com18:publisher.location4:homee") => { "publisher" "bob", "publisher-webpage" "www.example.com", "publisher.location" "home" } )