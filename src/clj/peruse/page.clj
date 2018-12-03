(ns peruse.page
  (:require [environ.core :refer [env]]
            [hiccup.page :refer [html5 include-css include-js]]))


(defn head []
  [:head
   [:title "peruse"]
   (include-css "css/main.css")])

(def main-page
  (html5
    (head)
    [:body
     [:div#mount-target]
     (when (env :dev)
       (include-js "js/dev/goog/base.js"))
     (include-js "js/app.js")]))