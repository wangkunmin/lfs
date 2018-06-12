export const CyStyle = 'core {\n' +
  '    active-bg-color: #000;\n' +
  '    active-bg-opacity: 0.333;\n' +
  '    background-color: #000;\n' +
  '}\n' +
  '\n' +
  'edge {\n' +
  '\n' +
  '    font-family:Microsoft YaHei;\n' +
  '    curve-style:  bezier;\n' +
  '    label: data(nexus);\n' +
  '    opacity: 1;\n' +
  '    width: 3;\n' +
  '    font-size:12;\n' +
  '    target-arrow-shape: triangle;\n' +
  '    color:#666;\n' +
  '    text-background-opacity: 1;\n' +
  '    text-background-color: #fff;\n' +
  '    text-background-shape: roundrectangle;\n' +
  '    line-style:dashed;\n' +
  '    edge-text-rotation:autorotate;\n' +
  '\n' +
  '    control-point-step-size:100;\n' +
  '}\n' +
  '\n' +
  'node {\n' +
  '    width: 190;\n' +
  '    height:70;\n' +
  '    content: data(name);\n' +
  '    text-halign: center;\n' +
  '    text-valign: center;\n' +
  '    text-wrap: wrap;\n' +
  '    text-margin-y:data(marginy);\n' +
  '    shape: roundrectangle;\n' +
  '    font-size:14;\n' +
  '    font-family:Microsoft YaHei;\n' +
  '    border-width: 3;\n' +
  '    background-image: data(labels) ;\n' +
  '    background-image-opacity: data(opacities);\n' +
  '    background-position-x:0 18 36;\n' +
  '    background-position-y:0 0 0;\n' +
  '    background-width:16 16 16;\n' +
  '    background-height:16 16 16;\n' +
  '    background-height-relative-to:inner;\n' +
  '    background-color:#fff;\n' +
  '    opacity: 1;\n' +
  '    transition-property:visibility,opacity;\n' +
  '    transition-duration:1s;\n' +
  '    transition-timing-function:cubic-bezier(.25,.8,.25,1);\n' +
  '}\n' +
  'node[label = "1"]{\n' +
  '    border-color: #9EBCF7;\n' +
  '    color: #000;\n' +
  '}\n' +
  'node[label = "2"]{\n' +
  '    width: 100;\n' +
  '    border-color: #97CC85;\n' +
  '    color: #000;\n' +
  '}\n' +
  'node[label = "3"]{\n' +
  '    border-color: #FE8572;\n' +
  '    color: #000;\n' +
  '}\n' +
  'node[label = "4"]{\n' +
  '    background-color: #006;\n' +
  '    color: #fff;\n' +
  '}\n' +
  'node[label = "5"]{\n' +
  '    background-color: #999;\n' +
  '    color: #fff;\n' +
  '}\n' +
  '\n' +
  'edge[type = "1"] {\n' +
  '    line-color: #89C3EB;\n' +
  '    source-arrow-color: #89C3EB;\n' +
  '    target-arrow-color: #89C3EB;\n' +
  '    z-index: 9;\n' +
  '    /*opacity: 0.666;*/\n' +
  '}\n' +
  '\n' +
  'edge[type = "2"] {\n' +
  '    line-color: #97CC85;\n' +
  '    source-arrow-color: #97CC85;\n' +
  '    target-arrow-color: #97CC85;\n' +
  '    z-index: 9;\n' +
  '    /*opacity: 0.666;*/\n' +
  '}\n' +
  '\n' +
  'edge[type = "3"] {\n' +
  '    line-color: #32A1AF;\n' +
  '    z-index: 9;\n' +
  '    /*opacity: 0.666;*/\n' +
  '}\n' +
  '\n' +
  'edge[type = "4"] {\n' +
  '    line-color: #3366CC;\n' +
  '    source-arrow-color: #3366CC;\n' +
  '    target-arrow-color: #3366CC;\n' +
  '    z-index: 9;\n' +
  '    /*opacity: 0.666;*/\n' +
  '}\n' +
  '\n' +
  'edge[type = "5"] {\n' +
  '    line-color: #FACD37;\n' +
  '    source-arrow-color: #FACD37;\n' +
  '    target-arrow-color: #FACD37;\n' +
  '    z-index: 9;\n' +
  '    /*opacity: 0.666;*/\n' +
  '}\n' +
  '\n' +
  'edge[type = "6"] {\n' +
  '    line-color: #4206A9;\n' +
  '    source-arrow-color: #4206A9;\n' +
  '    target-arrow-color: #4206A9;\n' +
  '    z-index: 9;\n' +
  '    /*opacity: 0.666;*/\n' +
  '}\n' +
  '\n' +
  'edge[type = "7"] {\n' +
  '    line-color: #E56765;\n' +
  '    opacity: 0.666;\n' +
  '    z-index: 9;\n' +
  '    width: 4;\n' +
  '    /*opacity: 0.666;*/\n' +
  '}\n' +
  '\n' +
  'edge[type = "8"] {\n' +
  '    line-color: #FACD37;\n' +
  '    opacity: 0.666;\n' +
  '    z-index: 9;\n' +
  '    width: 4;\n' +
  '    /*opacity: 0.666;*/\n' +
  '}\n' +
  '\n' +
  'edge[type = "9"] {\n' +
  '    line-color: #FACD37;\n' +
  '    opacity: 0.666;\n' +
  '    z-index: 9;\n' +
  '    width: 4;\n' +
  '    /*opacity: 0.666;*/\n' +
  '}\n' +
  '\n' +
  'edge[type = "10"] {\n' +
  '    line-color: #FACD37;\n' +
  '    /*opacity: 0.666;*/\n' +
  '    z-index: 9;\n' +
  '    width: 4;\n' +
  '}\n' +
  '\n' +
  'edge[type = "11"] {\n' +
  '    line-color: #3366CC;\n' +
  '    source-arrow-color: #3366CC;\n' +
  '    target-arrow-color: #3366CC;\n' +
  '    z-index: 9;\n' +
  '    /*opacity: 0.666;*/\n' +
  '}\n' +
  '\n' +
  'edge[type = "12"] {\n' +
  '    line-color: #FACD37;\n' +
  '    opacity: 0.666;\n' +
  '    z-index: 9;\n' +
  '    /*opacity: 0.666;*/\n' +
  '    width: 4;\n' +
  '}\n' +
  '\n' +
  '\n' +
  '\n' +
  '/*隐藏节点*/\n' +
  '.hiddening {\n' +
  '    opacity: 0;\n' +
  '}\n' +
  '.hidden{\n' +
  '    visibility:hidden;\n' +
  '    display: none;\n' +
  '}\n' +
  '\n' +
  '\n' +
  '\n' +
  '\n' +
  'node.clicked{\n' +
  '\n' +
  '    z-index: 999;\n' +
  '    border-width: 8;\n' +
  '    background-position-x:4 22 40;\n' +
  '    background-position-y:4 4 4;\n' +
  '}\n' +
  '\n' +
  'node.select{\n' +
  '    width:240;\n' +
  '    height:75;\n' +
  '    background-color: #7D7D7D;\n' +
  '    color:#fff;\n' +
  '}\n' +
  'node:selected {\n' +
  '    border-opacity: 0.5;\n' +
  '    border-width: 13;\n' +
  '    background-position-x:6 24 42;\n' +
  '    background-position-y:6 6 6;\n' +
  '}\n' +
  '\n' +
  'edge:selected {\n' +
  '    line-style:solid;\n' +
  '    width: 5;\n' +
  '}\n' +
  '\n' +
  '\n' +
  '\n' +
  '\n' +
  'node.search {\n' +
  '    background-color: #00acac;\n' +
  '    color:#fff;\n' +
  '}\n' +
  '\n' +
  '/*风险信息筛选样式*/\n' +
  '.faded {\n' +
  '    /*events: no;*/\n' +
  '}\n' +
  'edge.faded {\n' +
  '    opacity: 0.2;\n' +
  '    text-background-color: #0F0F0F;\n' +
  '}\n' +
  'node.faded{\n' +
  '    opacity: 0.2;\n' +
  '}\n' +
  'node[risk_label].faded{\n' +
  '    opacity: 1;\n' +
  '    color:#fff;\n' +
  '    border-width: 0;\n' +
  '    background-color: #FC4256;\n' +
  '}\n' +
  'node[!risk_label].faded{\n' +
  '    color:#000;\n' +
  '    border-width: 3;\n' +
  '    opacity: 0.1;\n' +
  '    background-color:#fff;\n' +
  '}\n';
