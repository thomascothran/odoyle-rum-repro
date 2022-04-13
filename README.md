# O'Doyle Rum Hot Reloading Repro

1. Run `./bin/dev` to start
2. Open `http://localhost/8000` in the browser
3. Make changes as repl. Things work as expected.
4. Trigger a hot reload by changing `app.cljc`. Shadow will pick up on the file change and reload for you.

At the final step, you will see the white screen of death with an error message:

```
rum-rules-repro.app/app cannot render because the :what block doesn't have a complete match yet
```

However, you will also see a log message showing what is in the session:

```clojurescript
{:message "Init! called", :session-state [[:game :game/time 0]]
```

So there should be a match.

Changing the `session*` `defonce` to `def` prevents the error, at the cost of losing state on every reload

