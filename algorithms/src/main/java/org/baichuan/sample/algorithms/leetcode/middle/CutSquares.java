package org.baichuan.sample.algorithms.leetcode.middle;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/12
 * https://leetcode-cn.com/problems/bisect-squares-lcci/
 * 面试题 16.13. 平分正方形
 *
 * 给定两个正方形及一个二维平面。请找出将这两个正方形分割成两半的一条直线。假设正方形顶边和底边与 x 轴平行。
 *
 * 每个正方形的数据square包含3个数值，正方形的左下顶点坐标[X,Y] = [square[0],square[1]]，以及正方形的边长square[2]。所求直线穿过两个正方形会形成4个交点，请返回4个交点形成线段的两端点坐标（两个端点即为4个交点中距离最远的2个点，这2个点所连成的线段一定会穿过另外2个交点）。2个端点坐标[X1,Y1]和[X2,Y2]的返回格式为{X1,Y1,X2,Y2}，要求若X1 != X2，需保证X1 < X2，否则需保证Y1 <= Y2。
 *
 * 若同时有多条直线满足要求，则选择斜率最大的一条计算并返回（与Y轴平行的直线视为斜率无穷大）。
 *
 * 示例：
 *
 * 输入：
 * square1 = {-1, -1, 2}
 * square2 = {0, -1, 2}
 * 输出： {-1,0,2,0}
 * 解释： 直线 y = 0 能将两个正方形同时分为等面积的两部分，返回的两线段端点为[-1,0]和[2,0]
 * 提示：
 *
 * square.length == 3
 * square[2] > 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bisect-squares-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CutSquares {
    public static void main(String[] args) {
        //[-1,1,5]
        //[-2,-2,7]
        //double[] doubles = new CutSquares().cutSquares(new int[]{-1, 1, 5}, new int[]{-2, -2, 7});
        //[-2,-2,3]
        //[0,0,4]
        //double[] doubles = new CutSquares().cutSquares(new int[]{-2, -2, 3}, new int[]{0, 0, 4});
        //[249,-199,5]
        //[-1,136,76]
        double[] doubles = new CutSquares().cutSquares(new int[]{249, -199, 5}, new int[]{-1, 136, 76});
        for (double d : doubles) {
            System.out.println(d);
        }
    }

    public double[] cutSquares(int[] square1, int[] square2) {
        double ls1 = square1[2] / 2.0;
        double ls2 = square2[2] / 2.0;
        double[] centre1 = new double[]{(square1[0] + ls1), (square1[1] + ls1)};
        double[] centre2 = new double[]{(square2[0] + ls2), (square2[1] + ls2)};

        //横坐标相等，则结果直线肯定是与y轴平行
        if (centre1[0] - centre2[0] == 0) {
            return new double[]{centre1[0], Math.min(square1[1], square2[1]), centre1[0], Math.max(square1[1] + square1[2], square2[1] + square2[2])};
        }
        double slope = (centre1[1] - centre2[1]) / (centre1[0] - centre2[0]);
        double offset = centre1[1] - (slope * centre1[0]);

        //左右两条边对应的直线与正方形中心对应的直线的交点的纵坐标
        double leftY1 = slope * square1[0] + offset;
        double rightY1 = slope * (square1[0] + square1[2]) + offset;

        double leftY2 = slope * square2[0] + offset;
        double rightY2 = slope * (square2[0] + square2[2]) + offset;

        //上下两条边对应的直线与正方形中心对应的直线的交点的横坐标
        double downX1 = (square1[1] - offset) / slope;
        double upX1 = (square1[1] + ls1 * 2 - offset) / slope;

        double downX2 = (square2[1] - offset) / slope;
        double upX2 = (square2[1] + ls2 * 2 - offset) / slope;

        //斜率在[-1,1]意味着交点在左右边，斜率在(-∞,-1),(1,+∞)意味着交点在上下边
        if (slope <= 1 && slope >= -1) {
            //斜率大于0，则更左边的那条边的交点的纵坐标也会更小
            if (slope > 0) {
                return new double[]{Math.min(square1[0], square2[0]), Math.min(leftY1, leftY2), Math.max(square1[0] + square1[2], square2[0] + square2[2]), Math.max(rightY1, rightY2)};
            } else {
                return new double[]{Math.min(square1[0], square2[0]), Math.max(leftY1, leftY2), Math.max(square1[0] + square1[2], square2[0] + square2[2]), Math.min(rightY1, rightY2)};
            }
        } else {
            if (slope > 0) {
                //斜率大于0，则更下边的那条边的交点的横坐标也会更小，所以放在前面
                return new double[]{Math.min(downX1, downX2), Math.min(square1[1], square2[1]), Math.max(upX1, upX2), Math.max(square1[1] + square1[2], square2[1] + square2[2])};
            } else {
                return new double[]{Math.min(upX1, upX2), Math.max(square1[1] + square1[2], square2[1] + square2[2]), Math.max(downX1, downX2), Math.min(square1[1], square2[1])};
            }
        }
    }
}
