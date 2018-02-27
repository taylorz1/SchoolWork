<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="a" />
        <signal name="ci" />
        <signal name="XLXN_4" />
        <signal name="r" />
        <signal name="XLXN_6" />
        <signal name="XLXN_7" />
        <signal name="XLXN_8" />
        <signal name="XLXN_9" />
        <signal name="XLXN_10" />
        <signal name="co" />
        <signal name="op(0)" />
        <signal name="op(1)" />
        <signal name="op(2:0)" />
        <signal name="XLXN_16" />
        <signal name="XLXN_17" />
        <signal name="XLXN_18" />
        <signal name="b" />
        <signal name="XLXN_20" />
        <signal name="XLXN_21" />
        <signal name="op(2)" />
        <signal name="less" />
        <port polarity="Input" name="a" />
        <port polarity="Input" name="ci" />
        <port polarity="Output" name="r" />
        <port polarity="Output" name="co" />
        <port polarity="Input" name="op(2:0)" />
        <port polarity="Input" name="b" />
        <port polarity="Input" name="less" />
        <blockdef name="add1b">
            <timestamp>2016-9-14T16:19:37</timestamp>
            <rect width="256" x="64" y="-192" height="192" />
            <line x2="0" y1="-160" y2="-160" x1="64" />
            <line x2="0" y1="-96" y2="-96" x1="64" />
            <line x2="0" y1="-32" y2="-32" x1="64" />
            <line x2="384" y1="-160" y2="-160" x1="320" />
            <line x2="384" y1="-32" y2="-32" x1="320" />
        </blockdef>
        <blockdef name="and2">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-64" y2="-64" x1="0" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="192" y1="-96" y2="-96" x1="256" />
            <arc ex="144" ey="-144" sx="144" sy="-48" r="48" cx="144" cy="-96" />
            <line x2="64" y1="-48" y2="-48" x1="144" />
            <line x2="144" y1="-144" y2="-144" x1="64" />
            <line x2="64" y1="-48" y2="-144" x1="64" />
        </blockdef>
        <blockdef name="or2">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-64" y2="-64" x1="0" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="192" y1="-96" y2="-96" x1="256" />
            <arc ex="192" ey="-96" sx="112" sy="-48" r="88" cx="116" cy="-136" />
            <arc ex="48" ey="-144" sx="48" sy="-48" r="56" cx="16" cy="-96" />
            <line x2="48" y1="-144" y2="-144" x1="112" />
            <arc ex="112" ey="-144" sx="192" sy="-96" r="88" cx="116" cy="-56" />
            <line x2="48" y1="-48" y2="-48" x1="112" />
        </blockdef>
        <blockdef name="m4_1e">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="96" y1="-416" y2="-416" x1="0" />
            <line x2="96" y1="-352" y2="-352" x1="0" />
            <line x2="96" y1="-288" y2="-288" x1="0" />
            <line x2="96" y1="-224" y2="-224" x1="0" />
            <line x2="96" y1="-32" y2="-32" x1="0" />
            <line x2="256" y1="-320" y2="-320" x1="320" />
            <line x2="96" y1="-160" y2="-160" x1="0" />
            <line x2="96" y1="-96" y2="-96" x1="0" />
            <line x2="96" y1="-96" y2="-96" x1="176" />
            <line x2="176" y1="-208" y2="-96" x1="176" />
            <line x2="96" y1="-32" y2="-32" x1="224" />
            <line x2="224" y1="-216" y2="-32" x1="224" />
            <line x2="96" y1="-224" y2="-192" x1="256" />
            <line x2="256" y1="-416" y2="-224" x1="256" />
            <line x2="256" y1="-448" y2="-416" x1="96" />
            <line x2="96" y1="-192" y2="-448" x1="96" />
            <line x2="96" y1="-160" y2="-160" x1="128" />
            <line x2="128" y1="-200" y2="-160" x1="128" />
        </blockdef>
        <blockdef name="vcc">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-32" y2="-64" x1="64" />
            <line x2="64" y1="0" y2="-32" x1="64" />
            <line x2="32" y1="-64" y2="-64" x1="96" />
        </blockdef>
        <blockdef name="m2_1">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="96" y1="-64" y2="-192" x1="96" />
            <line x2="96" y1="-96" y2="-64" x1="256" />
            <line x2="256" y1="-160" y2="-96" x1="256" />
            <line x2="256" y1="-192" y2="-160" x1="96" />
            <line x2="96" y1="-32" y2="-32" x1="176" />
            <line x2="176" y1="-80" y2="-32" x1="176" />
            <line x2="96" y1="-32" y2="-32" x1="0" />
            <line x2="256" y1="-128" y2="-128" x1="320" />
            <line x2="96" y1="-96" y2="-96" x1="0" />
            <line x2="96" y1="-160" y2="-160" x1="0" />
        </blockdef>
        <blockdef name="inv">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-32" y2="-32" x1="0" />
            <line x2="160" y1="-32" y2="-32" x1="224" />
            <line x2="128" y1="-64" y2="-32" x1="64" />
            <line x2="64" y1="-32" y2="0" x1="128" />
            <line x2="64" y1="0" y2="-64" x1="64" />
            <circle r="16" cx="144" cy="-32" />
        </blockdef>
        <block symbolname="add1b" name="XLXI_1">
            <blockpin signalname="ci" name="ci" />
            <blockpin signalname="XLXN_21" name="b" />
            <blockpin signalname="a" name="a" />
            <blockpin signalname="XLXN_10" name="r" />
            <blockpin signalname="co" name="co" />
        </block>
        <block symbolname="and2" name="XLXI_2">
            <blockpin signalname="b" name="I0" />
            <blockpin signalname="a" name="I1" />
            <blockpin signalname="XLXN_8" name="O" />
        </block>
        <block symbolname="or2" name="XLXI_3">
            <blockpin signalname="b" name="I0" />
            <blockpin signalname="a" name="I1" />
            <blockpin signalname="XLXN_9" name="O" />
        </block>
        <block symbolname="m4_1e" name="XLXI_4">
            <blockpin signalname="XLXN_8" name="D0" />
            <blockpin signalname="XLXN_9" name="D1" />
            <blockpin signalname="XLXN_10" name="D2" />
            <blockpin signalname="less" name="D3" />
            <blockpin signalname="XLXN_4" name="E" />
            <blockpin signalname="op(0)" name="S0" />
            <blockpin signalname="op(1)" name="S1" />
            <blockpin signalname="r" name="O" />
        </block>
        <block symbolname="vcc" name="XLXI_5">
            <blockpin signalname="XLXN_4" name="P" />
        </block>
        <block symbolname="m2_1" name="XLXI_6">
            <blockpin signalname="b" name="D0" />
            <blockpin signalname="XLXN_18" name="D1" />
            <blockpin signalname="op(2)" name="S0" />
            <blockpin signalname="XLXN_21" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_7">
            <blockpin signalname="b" name="I" />
            <blockpin signalname="XLXN_18" name="O" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <instance x="576" y="1392" name="XLXI_1" orien="R0">
        </instance>
        <instance x="496" y="608" name="XLXI_2" orien="R0" />
        <instance x="560" y="896" name="XLXI_3" orien="R0" />
        <instance x="1216" y="960" name="XLXI_4" orien="R0" />
        <branch name="a">
            <wire x2="208" y1="480" y2="480" x1="192" />
            <wire x2="496" y1="480" y2="480" x1="208" />
            <wire x2="208" y1="480" y2="736" x1="208" />
            <wire x2="208" y1="736" y2="1360" x1="208" />
            <wire x2="576" y1="1360" y2="1360" x1="208" />
            <wire x2="384" y1="736" y2="736" x1="208" />
            <wire x2="384" y1="736" y2="768" x1="384" />
            <wire x2="560" y1="768" y2="768" x1="384" />
        </branch>
        <iomarker fontsize="28" x="192" y="480" name="a" orien="R180" />
        <iomarker fontsize="28" x="192" y="544" name="b" orien="R180" />
        <branch name="ci">
            <wire x2="576" y1="1232" y2="1232" x1="176" />
        </branch>
        <iomarker fontsize="28" x="176" y="1232" name="ci" orien="R180" />
        <branch name="XLXN_4">
            <wire x2="1216" y1="928" y2="1120" x1="1216" />
            <wire x2="1440" y1="1120" y2="1120" x1="1216" />
        </branch>
        <instance x="1376" y="1120" name="XLXI_5" orien="R0" />
        <branch name="r">
            <wire x2="1616" y1="640" y2="640" x1="1536" />
        </branch>
        <iomarker fontsize="28" x="1616" y="640" name="r" orien="R0" />
        <branch name="XLXN_8">
            <wire x2="976" y1="512" y2="512" x1="752" />
            <wire x2="976" y1="512" y2="544" x1="976" />
            <wire x2="1216" y1="544" y2="544" x1="976" />
        </branch>
        <branch name="XLXN_9">
            <wire x2="1008" y1="800" y2="800" x1="816" />
            <wire x2="1008" y1="608" y2="800" x1="1008" />
            <wire x2="1216" y1="608" y2="608" x1="1008" />
        </branch>
        <branch name="XLXN_10">
            <wire x2="1088" y1="1232" y2="1232" x1="960" />
            <wire x2="1088" y1="672" y2="1232" x1="1088" />
            <wire x2="1216" y1="672" y2="672" x1="1088" />
        </branch>
        <branch name="co">
            <wire x2="992" y1="1360" y2="1360" x1="960" />
        </branch>
        <iomarker fontsize="28" x="992" y="1360" name="co" orien="R0" />
        <branch name="op(0)">
            <attrtext style="alignment:SOFT-VRIGHT;fontsize:28;fontname:Arial" attrname="Name" x="1152" y="1424" type="branch" />
            <wire x2="1152" y1="800" y2="1424" x1="1152" />
            <wire x2="1216" y1="800" y2="800" x1="1152" />
        </branch>
        <branch name="op(1)">
            <attrtext style="alignment:SOFT-BCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1184" y="864" type="branch" />
            <wire x2="1184" y1="864" y2="1440" x1="1184" />
            <wire x2="1312" y1="1440" y2="1440" x1="1184" />
            <wire x2="1216" y1="864" y2="864" x1="1184" />
        </branch>
        <branch name="op(2:0)">
            <wire x2="304" y1="304" y2="304" x1="192" />
        </branch>
        <iomarker fontsize="28" x="192" y="304" name="op(2:0)" orien="R180" />
        <instance x="560" y="1776" name="XLXI_6" orien="R0" />
        <branch name="XLXN_18">
            <wire x2="560" y1="1680" y2="1680" x1="528" />
        </branch>
        <instance x="304" y="1712" name="XLXI_7" orien="R0" />
        <branch name="b">
            <wire x2="272" y1="544" y2="544" x1="192" />
            <wire x2="496" y1="544" y2="544" x1="272" />
            <wire x2="272" y1="544" y2="1024" x1="272" />
            <wire x2="416" y1="1024" y2="1024" x1="272" />
            <wire x2="272" y1="1024" y2="1104" x1="272" />
            <wire x2="272" y1="1104" y2="1568" x1="272" />
            <wire x2="272" y1="1568" y2="1680" x1="272" />
            <wire x2="304" y1="1680" y2="1680" x1="272" />
            <wire x2="416" y1="1568" y2="1568" x1="272" />
            <wire x2="416" y1="1568" y2="1616" x1="416" />
            <wire x2="560" y1="1616" y2="1616" x1="416" />
            <wire x2="416" y1="832" y2="1024" x1="416" />
            <wire x2="560" y1="832" y2="832" x1="416" />
        </branch>
        <branch name="XLXN_21">
            <wire x2="576" y1="1296" y2="1296" x1="512" />
            <wire x2="512" y1="1296" y2="1456" x1="512" />
            <wire x2="944" y1="1456" y2="1456" x1="512" />
            <wire x2="944" y1="1456" y2="1648" x1="944" />
            <wire x2="944" y1="1648" y2="1648" x1="880" />
        </branch>
        <branch name="op(2)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="288" y="1808" type="branch" />
            <wire x2="560" y1="1808" y2="1808" x1="288" />
            <wire x2="560" y1="1744" y2="1808" x1="560" />
        </branch>
        <branch name="less">
            <wire x2="496" y1="1152" y2="1152" x1="144" />
            <wire x2="496" y1="736" y2="1152" x1="496" />
            <wire x2="1216" y1="736" y2="736" x1="496" />
        </branch>
        <iomarker fontsize="28" x="144" y="1152" name="less" orien="R180" />
    </sheet>
</drawing>