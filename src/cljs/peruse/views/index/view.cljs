(ns peruse.views.index.view
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [peruse.views.index.events]
            [peruse.views.index.subs]
            [peruse.components.golden-layout :as gl]
            [peruse.views.editor.view :as editor]))


(def config {:settings {:showCloseIcon false}
             :content  [{:type "row"
                         :content [{:type "react-component"
                                    :component "clj-in"
                                    :title "Edit"
                                    :isClosable false}
                                   {:type "react-component"
                                    :component "output-box"
                                    :title "Output"
                                    :isClosable false}]}]})

(def output-box
  (r/create-class
    {:reagent-render
     (fn []
       (let [output @(rf/subscribe [:index-output])]
         [:div.output-container
          (map (fn [os]
                 [:div.output-entry (str "=> " os)])
               output)]))}))

(def clj-in
  (r/create-class {:reagent-render
                   (fn []
                     [:div {:style {:width  "100%"
                                    :height "100%"}}
                      [editor/component "myedit"]])}))

(def comp-registry {"clj-in"     clj-in
                    "output-box" output-box})

(defn component []
  [gl/component "app-view" config comp-registry])