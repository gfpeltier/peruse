(ns peruse.core
  (:use compojure.core)
  (:require [clojure.core.async :refer [thread]]
            [immutant.web :as web]
            [compojure.route :as route]
            [ring.middleware.transit :refer [wrap-transit-response]]
            [ring.middleware.params :as mp]
            [peruse.middleware :as local-mid]
            [peruse.handlers :as handlers]
            [clojure.tools.logging :as log]))


(defroutes static-routes
           "The router."
           (GET "/" [] (handlers/index))
           (route/resources "/")
           (route/files "/" {:root "./target/cljs/public"}))

(defroutes api-routes
           (POST "/api" {:keys [body] :as req}
             (handlers/handle-clj body)))

(def app
  (routes
    (-> static-routes
        mp/wrap-params)
    (-> api-routes
        mp/wrap-params
        local-mid/wrap-body-transit)))

(defn run-srv! []
  (web/run app {:host "localhost" :port 3030 :path "/"}))

(defn start! []
  (web/run app {:host "localhost" :port 3030 :path "/"}))

(defn stop! []
  (web/stop))

(defn -main [& args]
  (thread (run-srv!))
  (loop [] (recur)))