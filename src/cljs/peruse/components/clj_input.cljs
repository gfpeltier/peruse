(ns peruse.components.clj-input
  (:require [reagent.core :as r]
            [re-frame.core :as rf]))


(def ^:const enter 13)

(defn component []
  (let [state (r/atom "")]
    (r/create-class
      {:reagent-render
       (fn []
         [:textarea {:rows        5
                     :cols        80
                     :on-change   #(reset! state (-> % .-target .-value))
                     :on-key-down (fn [e]
                                    (let [k (.-keyCode e)]
                                      (when (= k enter)
                                        (rf/dispatch [:send-clj @state]))))}])})))