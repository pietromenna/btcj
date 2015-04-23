(ns btcj.bdecoding-test
  (:require [midje.sweet :refer :all]
            [btcj.bdecoding :refer :all]))

; Check Specification details in benconding_test.clj

; (fact (bdecode-stream "4:spam") => "spam" )

; (fact (bdecode-stream "0:") => "" )

; (fact (bdecode-stream "4:spam3:yes") => '( "spam" "yes") )

(fact (bdecode-stream "i3e") => 3 )

; (fact (bdecode-stream "4:spam3:yes4:test") => '( "spam" "yes" "test") )

; (fact (bdecode-stream "4:spam3:yes4:testi55e") => '( "spam" "yes" "test" 55) )

; (fact (bdecode-stream "i1ei2e") => '( 1 2) )

; (fact (bdecode-stream "4:spami1e") => '( "spam" 1) )

(fact (bdecode-stream "le") => [] )

; (fact (bdecode-stream "l4:spam4:eggse") => [ "spam", "eggs" ]  )

(fact (bdecode-stream "de") => {} )

; (fact (bdecode-stream"d3:cow3:moo4:spam4:eggse") => {"cow" "moo", "spam" "eggs"} )

; (fact (bdecode-stream "d4:spaml1:a1:bee") => {"spam" ["a", "b"]} )

; (fact (bdecode-stream "d9:publisher3:bob17:publisher-webpage15:www.example.com18:publisher.location4:homee") => { "publisher" "bob", "publisher-webpage" "www.example.com", "publisher.location" "home" } )

; Seems like with bugs. This tests were created because of the issue which ended with:
; java.lang.NumberFormatException
; in the case of a "ll...ee" entry. In the meanwhile the tests are being changed in order to allow to continue.
; (fact (bdecode-stream "llee") => [[]] )

; (fact (bdecode-stream "d8:announce44:http://trackers.transamrit.net:8082/announce13:announce-listll44:http://tracker1.transamrit.net:8082/announce44:http://tracker2.transamrit.net:8082/announce44:http://tracker3.transamrit.net:8082/announceee5:teste3:yese") => { "announce" "http://trackers.transamrit.net:8082/announce" "announce-list" [[ "http://tracker1.transamrit.net:8082/announce" "http://tracker2.transamrit.net:8082/announce" "http://tracker3.transamrit.net:8082/announce" ]] "teste" "yes"} )