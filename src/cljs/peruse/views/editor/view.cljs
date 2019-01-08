(ns peruse.views.editor.view
  (:require [cljsjs.ace]
            [reagent.core :as r]
            [re-frame.core :as rf]))


(defn ctrl-enter-handler [editor]
  (let [text (.getValue (.-session editor))]
    (rf/dispatch [:send-clj text])))

(defn component [id]
  (r/create-class {:component-did-mount
                   (fn [_]
                     (let [editor (.edit js/ace id)
                           session (.-session editor)]
                       (.set (.-config js/ace) "basePath" "js/ace")
                       (.setMode session "ace/mode/clojure")
                       (.addCommand (.-commands editor)
                                    (clj->js {:name "submit"
                                              :bindKey {:win "Ctrl-Enter"
                                                         :mac "Command-Enter"}
                                              :exec ctrl-enter-handler}))))

                   :reagent-render
                   (fn [id]
                     [:div {:id id
                            :style {:width "100%"
                                    :height "100%"}}])}))