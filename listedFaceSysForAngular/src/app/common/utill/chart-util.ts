export class ChartUtil {

  constructor() { }

  draw(list,position) {
    let PI = Math. PI;
    // 0第一排的个数，1偏移量，2edge的class，3node的class
    let data = {
      t1: [5, 11*PI/9, 7*PI/9, 't1'],
      t2: [2, 17.5*PI/9, 2*PI/9, 't2'],
      t3: [3, 6*PI/9, 1*PI/9, 't3'],
      t5: [5, 19.9*PI/9, 1*PI/9, 't5'],
      t6: [5, 19.9*PI/9, 7*PI/9, 't6'],
      1:  [5, 19.9*PI/9, 7*PI/9, 'b2'],
      2:  [2, 17.5*PI/9, 2*PI/9, 'b2'],
      3:  [3, 8*PI/9, 1*PI/9, 'b3'],
      4:  [3, 8*PI/9, 1*PI/9, 'b4'],
      t4: [3, 8*PI/9, 1*PI/9, 'b4'],
      5:  [3, 8*PI/9, 1*PI/9, 'b5'],
      6:  [3, 8*PI/9, 7*PI/9, 'b6'],
      7:  [3, 8*PI/9, 7*PI/9, 'b7'],
      8:  [3, 8*PI/9, 7*PI/9, 'b8'],
      9:  [3, 8*PI/9, 7*PI/9, 'b9'],
      10: [3, 8*PI/9, 7*PI/9, 'b10'],
      11: [3, 8*PI/9, 7*PI/9, 'b11'],
      t11: [3,8*PI/9, 7*PI/9, 'b12'],
      12:  [3,8*PI/9, 7*PI/9, 'b12']
    };
    let nodeType = {
      1: ['company'],
      2: ['person'],
      3: ['security'],
      4: ['pfund'],
      5: ['pfcompany']
    };
    let nodes=[];
    let edges=[];
    for (let i in list) {
      let t = null, sl = data[i][2], num = null;
      t = data[i][1];
      num = data[i][0];
      let positions = this.qwe(list[i].length,num,t,sl,position);
      for (let j = 0; j < list[i].length; j++) {
        let edge = list[i][j].edge;
        let node = list[i][j].node;
        if (node.name) {
          nodes.push(this.getNode1(node, nodeType[node.label][0], positions[j]));
          edges.push(this.getEdge1(edge, data[i][3]));
        }
      }
    }
    return {
      nodes: nodes,
      edges: edges
    };
  }

  getNode1(data, classes, position) {

    data.labels = this.getType(data.company_type);

    if (data.labels.length === 1 && data.labels[0] === 'assets/images/svg/null.png') {
      data.marginy = '0';
      data.opacities = [0];
    } else {
      data.marginy = '8';
      data.opacities = [1, 1, 1];
    }

    let node = { group: 'nodes', position: position, data: data, classes: classes };
    node.data.name = data.name.replace(/(.{10}(?!$))/g, '$1\n');
    node.data.name = node.data.name.replace('\n\n', '\n');
    node.data.name = node.data.name.replace('\n\n', '\n');
    return node;
  };

  getEdge1(data, classes) {
    let edge = {
      data: {
        id: data.id,
        source: data.source_id,
        target: data.target_id,
        type: data.relation_type,
        nexus: this.chartFilter(data.nexus)
      },
      classes: `autorotate ${classes}`
    };
    if (edge.data.nexus === '0%' && data.relation_type === '1') {
      edge.data.nexus = '投资';
    }
    if (edge.data.nexus instanceof Object && data.relation_type === '5') {
      edge.data.nexus = '担保';
    }
    return edge;
  }

  getType(type) {
    if (type) {
      let cType = {
        A: 'assets/images/svg/chartCtype2.png',
        B: 'assets/images/svg/chartCtype2.png',
        H: 'assets/images/svg/chartCtype2.png',
        F: 'assets/images/svg/chartCtype1.png',
        P: 'assets/images/svg/chartCtype4.png',
        3: 'assets/images/svg/chartCtype3.png',
        'null': 'assets/images/svg/null.png'
      };
      let labels = [];
      for (let i = 0; i < type.length; i++) {
        if (labels.indexOf(cType[type[i]]) === -1) {
          labels.push(cType[type[i]]);
        }
      }
      return labels;
    } else {
      return ['assets/images/svg/null.png'];
    }
  };

  qwe(len, Nl, theta0, sl, position) {
    let x1 = 0, y1 = 0, r = 400, Ng = len;
    if (position) {
      x1 = position.x;
      y1 = position.y;
    }
    let positions = [];
    while(Ng > 0) {
      for (let j = 0; j < Math.min(Ng, Nl); j++) {
        let slice = sl / (Math.min(Ng, Nl));
        let x = r*Math.cos(theta0 + slice*j);
        let y = r*Math.sin(theta0 + slice*j);
        positions.push({ x: x+x1, y: y+y1 });
      }
      Ng -= Nl;     //#还剩余的点个数
      Nl = Nl + 1;  // 下一环个数增加1
      r = r + 120;  //#每增加一环，半径增加 50
    }
    positions.reverse();
    return positions;
  }

  chartFilter(input) {
    if (typeof input === 'number') {
      return input + '%';
    }
    if(typeof input === 'string') {
      return input;
    }
    return input;
  }
}
