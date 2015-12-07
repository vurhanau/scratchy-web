<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">
    <style>
        html {
            color: rgba(0, 0, 0, 0.87);
        }
        .rank {
            background-color: #ddd;
        }
        .rank-header {
            color: #646464;
        }
    </style>
</head>
<body>
    <div class="row">
        <div class="col s4 offset-s4 rank-header">
            <h2 class="header center-align">Top-${n} emoticons on Twitch</h3>
        </div>
    </div>
    <div class="row">
        <div class="col s4 offset-s4 rank">
          <ul class="collection">
            <#list topn as rank>
                <li class="collection-item avatar">
                  <img src="${rank.url}" alt="" class="circle">
                  <span class="title">#${rank.pos}</span>
                  <p>${rank.symbols}<br>
                  </p>
                  <a href="#!" class="secondary-content"><span class="badge">${rank.count} usages</span></a>
                </li>
            </#list>
          </ul>
        </div>
    </div>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script>
</body>
</html>
