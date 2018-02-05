/* jshint esversion: 6 */
(() => {
  'use strict';
  
  function setColor(x, y, color) {
    $(`.chain:nth-of-type(${y + 1}) .led:nth-of-type(${x + 1})`).css({
      background: color
    });
  }
  
  const webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/screenSocket");
  webSocket.onmessage = (event) => {
    const startTime = new Date().getTime();
    
    const data = event.data;
  
    for (let i = 0; i < data.length / 5; i++) {
      const d = data;
      const i5 = i * 5;
        
      const x = d.charCodeAt(0 + i5);
      const y = d.charCodeAt(1 + i5);
      const r = d.charCodeAt(2 + i5);
      const g = d.charCodeAt(3 + i5);
      const b = d.charCodeAt(4 + i5);
      
      const color = `rgb(${r}, ${g}, ${b})`;
      setColor(x, y, color);
    }
  
    const ellapsedTime = new Date().getTime() - startTime;
    $('.time').text(`${ellapsedTime} ms`);
  };

})();