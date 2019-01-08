(ns peruse.components.golden-layout
  (:require [reagent.core :as r]
            [cljsjs.jquery]
            [cljsjs.golden-layout]))


(defn make-layout
  ([config] (new js/GoldenLayout (clj->js config)))
  ([config elem-id] (new js/GoldenLayout (clj->js config) (.getElementById js/document elem-id))))

(defn register-component [layout name component] (.registerComponent layout name component))

(defn component [id config comps]
  (let [state (r/atom {})]
    (r/create-class {:component-did-mount
                     (fn [_]
                       (reset! state (make-layout config id))
                       (doseq [[k v] comps] (register-component @state k v))
                       (.init @state)
                       (.addEventListener js/window "resize" #(.updateSize @state)))

                     :component-will-unmount
                     (fn [] (.destroy @state))

                     :reagent-render
                     (fn [id]
                       [:div {:id id :style {:width "100vw" :height "100vh"}}])})))