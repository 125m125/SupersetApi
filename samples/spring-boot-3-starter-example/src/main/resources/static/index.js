"use strict";
(function () {
  function fetchGuestTokenFromBackend() {
    return fetch("/api/superset/guestToken", { method: "POST", body: new URLSearchParams({firstName: 'Test', lastName: 'User', guestName: 'testUser123'}) }).then((x) =>
      x.text()
    );
  }
  function fetchEmbedDatailsFromBackend() {
    return fetch("/api/superset/details").then((x) => x.json());
  }

  // superset behaves strange when the token is loaded in fetchGuestToken on the first attempt.
  // Refreshes are fine. Therefore we preload the first token and load later tokens interactively.
  Promise.all([
    fetchGuestTokenFromBackend(),
    fetchEmbedDatailsFromBackend(),
  ]).then(([token, { id, rootUrl }]) => {
    var first = true;
    supersetEmbeddedSdk.embedDashboard({
      id, // given by the Superset embedding UI
      supersetDomain: rootUrl,
      mountPoint: document.getElementById("superset-placeholder"), // any html element that can contain an iframe
      fetchGuestToken: () => {
        if (first) {
          first = false;
          return Promise.resolve(token);
        }
        return fetchGuestTokenFromBackend();
      },
      dashboardUiConfig: {
        // dashboard UI config: hideTitle, hideTab, hideChartControls, filters.visible, filters.expanded (optional)
        hideTitle: true,
        filters: {
          expanded: false,
        },
      },
    });
  });
})();