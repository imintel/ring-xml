(ns ring.middleware.test.xml
  (:use ring.middleware.xml
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