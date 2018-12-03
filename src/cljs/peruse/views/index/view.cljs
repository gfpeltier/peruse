(ns peruse.views.index.view
  (:require [re-frame.core :as rf]
            [peruse.components.clj-input :as clj-input]
            [peruse.views.index.events]
            [peruse.views.index.subs]))


(defn output-box []
  (let [output @(rf/subscribe [:index-output])]
    [:div.output-container
     (map (fn [os]
            [:div.output-entry (str "=> " os)])
          output)]))

(defn component []
  [:div
   [:h1 "peruse"]
   [:h3 "REPL"]
   [clj-input/component]
   [:h3 "Output"]
   [output-box]])