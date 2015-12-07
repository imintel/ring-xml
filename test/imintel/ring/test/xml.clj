(ns imintel.ring.test.xml
  (:use imintel.ring.xml
        clojure.test 
        ring.util.io))

(deftest test-xml-request
  (let [handler (wrap-xml-request identity)]
    (testing "xml body"
      (let [request   {:content-type "application/xml"
                       :body (string-input-stream "<xml></xml>")}
            response  (handler request)]
        (is (= [{:tag :xml :attrs nil :content nil} nil] (:body response)))))))

(deftest test-xml-response
  (testing "properly formatted vector/map body" 
    (let [handler (constantly {:status 200 :headers {} 
                               :body [{:tag :xml :attrs nil :content nil} nil]})
          response ((wrap-xml-response handler) {})]
      (is (= (get-in response [:headers "Content-Type"]) "application/xml; charset=utf-8"))
      (println response)
      (is (= (:body response) "<xml/>")))))

(deftest test-invalid-xml-returns-400
  (let [handler (wrap-xml-request identity)]
      (let [request   {:content-type "application/xml"
                       :body (string-input-stream "<xml></XML>")}
            response  (handler request)]
      (is (= (:status response) 400))
      (is (= (get-in response [:headers "Content-Type"]) "text/plain"))
      (is (= (:body response) "The element type \"xml\" must be terminated by the matching end-tag \"</xml>\".")))))
