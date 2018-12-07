(ns peruse.views.sandbox.view
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [peruse.views.sandbox.repl :as repl-view]
            [cljsjs.jquery]
            [cljsjs.golden-layout]))

(def content (clj->js {:content [{:type "row"
                                  :content [{:type "react-component"
                                             :component "clj-in"
                                             :props {:label "A"}
                                             :title "REPL"}
                                            {:type "react-component"
                                             :component "output-box"
                                             :props {:label "B"}
                                             :title "Output"}]}]}))

(def output-box
  (r/create-class
    {:reagent-render
     (fn []
       (let [output @(rf/subscribe [:index-output])]
         [:div.output-container
          (map (fn [os]
                 [:div.output-entry (str "=> " os)])
               output)]))}))

(defn init-gl! []
  (let [layout (js/GoldenLayout. content (.getElementById js/document "layout-container"))
        _ (.registerComponent layout "clj-in" repl-view/component)
        _ (.registerComponent layout "output-box" output-box)]
    (.init layout)))

(defn component []
  (r/create-class {:component-did-mount
                   (fn []
                     (init-gl!))

                   :reagent-render
                   (fn []
                     [:div#layout-container])}))
