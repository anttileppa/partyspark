/**
function refresh() {
  $.getJSON("/screen", (response) => {
    const result = response.data;
    
    for (let y = 0; y < response.chains; y++) {
      for (let x = 0; x < response.leds; x++) {
        $(`.chain:nth-of-type(${y + 1}) .led:nth-of-type(${x + 1})`).css({
          background: result[y][x]
        });
      }
    }
    
    setTimeout(() => {
      refresh();
    }, 10);
  });
}

$(document).ready(() => {
  refresh();
});
**/

function setColor(x, y, color) {
  $(`.chain:nth-of-type(${y + 1}) .led:nth-of-type(${x + 1})`).css({
    background: color
  }); 
}

const webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/screenSocket");
webSocket.onmessage = (event) => { 
  const data = JSON.parse(event.data);/**
  if (data.type === 'change') {**/
    data.forEach((change) => {
      const d = change;
      const x = d[0];
      const y = d[1];
      const r = d[2];
      const g = d[3];
      const b = d[4];
      const a = d.length === 6 ? d[5] / 255 : 1;
      
      const color = a < 1 ? `rgba(${r}, ${g}, ${b}, ${a})` : `rgb(${r}, ${g}, ${b})`;
      setColor(x, y, color);
    });
  //}
};
/** TODO: Reconetx
webSocket.onclose = function () { alert("WebSocket connection closed") };**/