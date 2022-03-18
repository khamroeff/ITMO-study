<template>
  <table class="main-structure-table">
    <tbody>
    <tr>
      <td class="control-panel">
        <button type="button" class="btn reset-colors material-icons" v-on:click="logout">logout</button>
        <div @mousemove="graphMouseMove" @mousedown="send">
          <svg height="300" width="300" xmlns="http://www.w3.org/2000/svg" id="graph" class="shadow-graph"
               style="pointer-events: none">
            <polygon fill="palevioletred" fill-opacity="0.3" points="117.5,147.5 147.5,147.5 147.5,87.5" id="figure1"/>

            <polygon fill="mediumspringgreen" fill-opacity="0.3" points="152.5,87.5 152.5,147.5 182.5,147.5 182.5,87.5"
                     id="figure2"/>
            <path d="M 147.5 182.5 L 147.5 152.5 L 117.5 152.5 C 117.5 172.5 127.5 182.5 147.5 182.5 Z"
                  fill="rgb(174, 193, 187)"
                  fill-opacity="0.3"
                  id="figure3"/>

            <line stroke="rgb(174, 193, 187)" x1="10" x2="290" y1="150" y2="150" stroke-width="5"
                  stroke-linecap="round"/>
            <line stroke="rgb(174, 193, 187)" x1="150" x2="150" y1="10" y2="290" stroke-width="5"
                  stroke-linecap="round"/>
            <polygon fill="rgb(174, 193, 187)" points="150,0 140,15 160,15"/>
            <polygon fill="rgb(174, 193, 187)" points="300,150 285,160 285,140"/>
            <g v-for="point in points" :key="point.id">
              <circle v-bind:cx="point.x * 20 + 150" v-bind:cy="(-point.y) * 20 + 150" r="4" class="littleDot"
                      v-bind:fill="point.insideArea ? 'mediumspringgreen' : 'palevioletred'"></circle>
            </g>
          </svg>
        </div>
        <div class="X_select">
          <p class="prompt-text">Введите X (-5 ... 3):</p>
          <div class="box-visual invert-colors">
            <div v-for="i in 9" :key="i">
              <input type="radio" name="x" :id="'x_' + (i - 6).toString().replace('-', '_')" :value="i - 6"
                     v-model="xValue">
              <label :for="'x_' + (i - 6).toString().replace('-', '_')">{{ i - 6 }}</label>
            </div>
          </div>
        </div>
        <div class="Y_select">
          <p class="prompt-text">Введите Y (-3 ... 3):</p>
          <input type="text" class="text-input" placeholder="Введите значение..." v-model="yValue">
        </div>
        <div class="R_select">
          <p class="prompt-text">Введите R (0 ... 3):</p>
          <div class="box-visual invert-colors">
            <div v-for="i in 9" :key="i">
              <input type="radio" name="r" :id="'r_' + (i - 6).toString().replace('-', '_')" :value="i - 6"
                     v-model="rValue" v-on:input="redrawFigure">
              <label :for="'r_' + (i - 6).toString().replace('-', '_')">{{ i - 6 }}</label>
            </div>
          </div>
        </div>
        <br/>
        <button class="btn confirm-colors material-icons" v-on:click="send">send</button>
        <button class="btn reset-colors material-icons" v-on:click="resetWithClear">backspace</button>
      </td>
      <td class="indent-box">
        <table class="result-table" id="result">
          <thead>
          <tr>
            <td>X</td>
            <td>Y</td>
            <td>R</td>
            <td>Входит?</td>
            <td>Время</td>
            <td>Выполнение</td>
          </tr>
          </thead>

          <tbody v-if="points">
          <tr v-for="point in points" :key="point.id">
            <td>{{ point.x }}</td>
            <td>{{ point.y }}</td>
            <td>{{ point.r }}</td>
            <td>{{ point.insideArea ? 'да' : 'нет' }}</td>
            <td>{{ point.timestamp }}</td>
            <td>{{ point.executionTime }}</td>
          </tr>
          </tbody>
        </table>
      </td>
    </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  name: "MainPage",
  data() {
    return {
      xValue: 0,
      yValue: 0,
      rValue: 3,
      points: undefined
    }
  },

  created: async function () {
    console.log('let me grab your ass and check your permissions for this page!!')

    let res = await fetch('http://localhost:3439/lab4back-1.0-SNAPSHOT/api/user', {
      credentials: 'include'
    });

    if (!res.ok) await this.$router.push('/')
    else {
      res = await fetch('http://localhost:3439/lab4back-1.0-SNAPSHOT/api/points', {
        method: 'GET',
        credentials: 'include'
      });

      if (res.ok) {
        this.points = await res.json();
      } else await this.$router.push('/')
    }
  },

  methods: {
    send: async function () {
      if (this.xValue === "") {
        alert('Неверное значение X')
        return;
        // TODO: maybe we should show this error message at the page?
      }

      const trueY = +this.yValue;
      if (this.yValue === "" || isNaN(trueY) || trueY < -5 || trueY > 3) {
        alert('Неверное значение Y')
        return;
        // TODO: maybe we should show this error message at the page?
      }

      if (this.rValue === "" || +this.rValue < 0) {
        alert('Неверное значение R')
        return;
        // TODO: maybe we should show this error message at the page?
      }

      const res = await fetch('http://localhost:3439/lab4back-1.0-SNAPSHOT/api/points', {
        method: 'POST',
        body: new URLSearchParams({
          x: this.xValue,
          y: this.yValue,
          r: this.rValue
        }),
        credentials: 'include'
      })
      if (!res.ok) {
        await this.$router.push("/")
      } else {
        const json = await res.json();
        this.points.push(json);
      }
    },
    reset: function () {
      this.xValue = 0;
      this.yValue = 0;
      this.rValue = 3;
    },
    resetWithClear: async function () {
      this.reset()

      await fetch('http://localhost:3439/lab4back-1.0-SNAPSHOT/api/points', {
        method: 'DELETE',
        credentials: 'include'
      })

      this.points = []
    },
    logout: async function () {
      await fetch('http://localhost:3439/lab4back-1.0-SNAPSHOT/api/user', {
        credentials: 'include',
        method: "DELETE"
      });
      document.cookie = "";
      await this.$router.push("/")
    },

    getCoords(elem) {
      let box = elem.getBoundingClientRect();

      return {
        top: box.top + window.pageYOffset,
        right: box.right + window.pageXOffset,
        bottom: box.bottom + window.pageYOffset,
        left: box.left + window.pageXOffset
      };
    },

    drawOMarker(x, y) {
      this.deleteOMarker();
      const svg = document.getElementById('graph');

      let circle = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
      circle.setAttributeNS(null, 'cx', (x * 20 + 150).toString());
      circle.setAttributeNS(null, 'cy', (-y * 20 + 150).toString());
      circle.setAttributeNS(null, 'r', '8');
      circle.setAttributeNS(null, 'stroke', 'rgb(174, 193, 187)');
      circle.setAttributeNS(null, 'stroke-width', '5');
      circle.setAttributeNS(null, 'fill-opacity', '0');
      circle.id = "selected_pos";
      svg.appendChild(circle);
    },

    deleteOMarker() {
      let circle = document.getElementById('selected_pos');
      if (circle !== null) circle.parentElement.removeChild(circle);
    },

    graphMouseMove: function (evt) {
      let offset = this.getCoords(evt.target);
      const width = offset.right - offset.left;
      const height = offset.bottom - offset.top;

      let x = Math.round((evt.pageX - offset.left - width / 2) / (width / 2) * (5 / 2 * 3));
      let y = -Number(((evt.pageY - offset.top - height / 2) / (height / 2) * (5 / 2 * 3)).toFixed(3));

      if (x < -5) {
        x = -5;
      } else if (x > 3) {
        x = 3;
      }

      if (y < -3) {
        y = -3;
      } else if (y > 3) {
        y = 3;
      }

      this.drawOMarker(x, y);

      this.xValue = x;
      this.yValue = y;
    },

    redrawFigure: function () {
      setTimeout(() => this.executeRedraw(this.rValue), 100);
    },

    executeRedraw(scale) {
      let figure1 = document.getElementById("figure1");
      if (scale < 0) scale = 0;

      figure1.setAttributeNS(null, 'points',
          (147.5 - 10 * scale) + ",147.5 " + "147.5,147.5 " + "147.5," + (147.5 - 20 * scale)
      );

      let figure2 = document.getElementById("figure2");
      figure2.setAttributeNS(null, 'points',
          "152.5," + (147.5 - 20 * scale) + " 152.5,147.5 " + (152.5 + 10 * scale) + ",147.5 " + (152.5 + 10 * scale) + "," + (147.5 - 20 * scale)
      );


      let figure3 = document.getElementById("figure3");
      if (scale > 0) {
        figure3.setAttributeNS(null, 'd',
            "M 147.5 " + (152.5 + 10 * scale) + " L 147.5 152.5 L " + (147.5 - 10 * scale) + " 152.5 C " + (147.5 - 10 * scale) + " " + (152.5 + 10 * (scale - 1)) + " " + (147.5 - 10 * (scale - 1)) + " " + (152.5 + 10 * scale) + " 147.5 " + (152.5 + 10 * scale) + " Z"
        );
      } else {
        figure3.setAttributeNS(null, 'd',
            "M 147.5 147.5 Z"
        );
      }
    }
  }
}
</script>