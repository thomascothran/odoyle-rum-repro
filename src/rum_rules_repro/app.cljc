(ns rum-rules-repro.app
  (:require #?(:cljs [cljs.pprint :refer [pprint]])
            [rum.core :as r]
            [odoyle.rum :as or]
            [odoyle.rules :as o]))

(def components
  (or/ruleset
   {app
    [:what [:game :game/time t]
     :then (let [*session (or/prop)]
             [:p (str "The time is now " t)])]}))

(defonce *session
  (atom (-> (reduce o/add-rule (o/->session) components)
            (o/insert :game :game/time 0)
            o/fire-rules)))

#?(:cljs
   (defn ^:dev/after-load init!
     []
     (pprint {:message "Init! called"
              :session-state (o/query-all @*session)})
     (r/mount (app *session) (js/document.querySelector "#app"))))

(comment
  (o/query-all @*session))
