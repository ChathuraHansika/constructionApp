import {Component, OnInit} from '@angular/core';
import Chart from 'chart.js';

declare var $: any;

@Component({
  selector: 'app-weather-report',
  templateUrl: './weather-report.component.html',
  styleUrls: ['./weather-report.component.scss']
})
export class WeatherReportComponent implements OnInit {

  constructor() {
    var data = {
      datasets: [{
        data: [10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10],
        backgroundColor: [
          "#1BFF08",
          "#0007b5",
          "#FF150F"
        ]
      }],
      labels: [
        "Fine",
        "Cloudy",
        "Rainy"
      ]
    };

    // console.log("Check One............");
    $(document).ready(
      function () {
        var canvas: any = document.getElementById("myChart");
        var ctx = canvas.getContext("2d");
        var myNewChart = new Chart(ctx, {
          type: 'pie',
          data: data
        });

        canvas.onclick = function (evt) {
          $('.ui.small.test.modal.add')
            .modal({
              blurring: true
            })
            .modal('show')
          ;
        }
        // canvas.onclick = function (evt) {
        //   var activePoints = myNewChart.getElementsAtEvent(evt);
        //   if (activePoints[0]) {
        //     var chartData = activePoints[0]['_chart'].config.data;
        //     var idx = activePoints[0]['_index'];
        //
        //     var label = chartData.labels[idx];
        //     var value = chartData.datasets[0].data[idx];
        //
        //     var url = "Weather : " + label + " --||-- " + "Weather Time : " + value;
        //     alert(url);
        //     canvas.addModal();
        //   }
        // };
      }
    );
  }

  ngOnInit() {
  }

  addModal() {
    $('.ui.small.test.modal.add')
      .modal({
        blurring: true
      })
      .modal('show')
    ;
  }
}

