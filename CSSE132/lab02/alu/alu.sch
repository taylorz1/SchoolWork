<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="XLXN_1" />
        <signal name="XLXN_2" />
        <signal name="XLXN_3" />
        <signal name="a(3:0)" />
        <signal name="b(3:0)" />
        <signal name="a(0)" />
        <signal name="a(1)" />
        <signal name="a(2)" />
        <signal name="a(3)" />
        <signal name="b(3)" />
        <signal name="b(2)" />
        <signal name="b(1)" />
        <signal name="b(0)" />
        <signal name="r(3:0)" />
        <signal name="r(0)" />
        <signal name="r(1)" />
        <signal name="r(2)" />
        <signal name="r(3)" />
        <signal name="co" />
        <signal name="op(2:0)" />
        <signal name="XLXN_21(2:0)" />
        <signal name="XLXN_22(2:0)" />
        <signal name="XLXN_23(2:0)" />
        <signal name="XLXN_24(2:0)" />
        <signal name="XLXN_27" />
        <signal name="op(2)" />
        <signal name="ci" />
        <signal name="XLXN_32" />
        <signal name="XLXN_33" />
        <signal name="XLXN_34" />
        <signal name="XLXN_35" />
        <signal name="XLXN_36" />
        <signal name="zero" />
        <signal name="overflow" />
        <port polarity="Input" name="a(3:0)" />
        <port polarity="Input" name="b(3:0)" />
        <port polarity="Output" name="r(3:0)" />
        <port polarity="Output" name="co" />
        <port polarity="Input" name="op(2:0)" />
        <port polarity="Input" name="ci" />
        <port polarity="Output" name="zero" />
        <port polarity="Output" name="overflow" />
        <blockdef name="alu1b">
            <timestamp>2016-9-14T17:27:42</timestamp>
            <line x2="0" y1="32" y2="32" x1="64" />
            <line x2="0" y1="-224" y2="-224" x1="64" />
            <line x2="0" y1="-160" y2="-160" x1="64" />
            <rect width="64" x="0" y="-108" height="24" />
            <line x2="0" y1="-96" y2="-96" x1="64" />
            <line x2="0" y1="-32" y2="-32" x1="64" />
            <line x2="384" y1="-224" y2="-224" x1="320" />
            <line x2="384" y1="-32" y2="-32" x1="320" />
            <rect width="256" x="64" y="-256" height="320" />
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
        <blockdef name="overflow_detect">
            <timestamp>2016-9-14T18:19:14</timestamp>
            <rect width="256" x="64" y="-256" height="256" />
            <line x2="0" y1="-224" y2="-224" x1="64" />
            <line x2="0" y1="-160" y2="-160" x1="64" />
            <line x2="0" y1="-96" y2="-96" x1="64" />
            <line x2="0" y1="-32" y2="-32" x1="64" />
            <line x2="384" y1="-224" y2="-224" x1="320" />
        </blockdef>
        <blockdef name="and4">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-112" y2="-112" x1="144" />
            <arc ex="144" ey="-208" sx="144" sy="-112" r="48" cx="144" cy="-160" />
            <line x2="144" y1="-208" y2="-208" x1="64" />
            <line x2="64" y1="-64" y2="-256" x1="64" />
            <line x2="192" y1="-160" y2="-160" x1="256" />
            <line x2="64" y1="-256" y2="-256" x1="0" />
            <line x2="64" y1="-192" y2="-192" x1="0" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="64" y1="-64" y2="-64" x1="0" />
        </blockdef>
        <blockdef name="inv4">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="160" y1="-32" y2="-32" x1="224" />
            <line x2="160" y1="-96" y2="-96" x1="224" />
            <line x2="160" y1="-160" y2="-160" x1="224" />
            <line x2="160" y1="-224" y2="-224" x1="224" />
            <line x2="64" y1="-32" y2="-32" x1="0" />
            <line x2="64" y1="-96" y2="-96" x1="0" />
            <line x2="64" y1="-160" y2="-160" x1="0" />
            <line x2="64" y1="-224" y2="-224" x1="0" />
            <line x2="128" y1="-256" y2="-224" x1="64" />
            <line x2="64" y1="-224" y2="-192" x1="128" />
            <line x2="64" y1="-192" y2="-256" x1="64" />
            <circle r="16" cx="144" cy="-32" />
            <line x2="128" y1="-64" y2="-32" x1="64" />
            <line x2="64" y1="-32" y2="0" x1="128" />
            <line x2="64" y1="0" y2="-64" x1="64" />
            <line x2="128" y1="-128" y2="-96" x1="64" />
            <line x2="64" y1="-96" y2="-64" x1="128" />
            <line x2="64" y1="-64" y2="-128" x1="64" />
            <circle r="16" cx="144" cy="-96" />
            <line x2="128" y1="-192" y2="-160" x1="64" />
            <line x2="64" y1="-160" y2="-128" x1="128" />
            <line x2="64" y1="-128" y2="-192" x1="64" />
            <circle r="16" cx="144" cy="-160" />
            <circle r="16" cx="144" cy="-224" />
        </blockdef>
        <block symbolname="alu1b" name="XLXI_2">
            <blockpin signalname="a(0)" name="a" />
            <blockpin signalname="XLXN_32" name="ci" />
            <blockpin signalname="op(2:0)" name="op(2:0)" />
            <blockpin signalname="b(0)" name="b" />
            <blockpin signalname="r(0)" name="r" />
            <blockpin signalname="XLXN_1" name="co" />
            <blockpin signalname="r(3)" name="less" />
        </block>
        <block symbolname="alu1b" name="XLXI_3">
            <blockpin signalname="a(1)" name="a" />
            <blockpin signalname="XLXN_1" name="ci" />
            <blockpin signalname="op(2:0)" name="op(2:0)" />
            <blockpin signalname="b(1)" name="b" />
            <blockpin signalname="r(1)" name="r" />
            <blockpin signalname="XLXN_2" name="co" />
            <blockpin name="less" />
        </block>
        <block symbolname="alu1b" name="XLXI_4">
            <blockpin signalname="a(2)" name="a" />
            <blockpin signalname="XLXN_2" name="ci" />
            <blockpin signalname="op(2:0)" name="op(2:0)" />
            <blockpin signalname="b(2)" name="b" />
            <blockpin signalname="r(2)" name="r" />
            <blockpin signalname="XLXN_3" name="co" />
            <blockpin name="less" />
        </block>
        <block symbolname="alu1b" name="XLXI_5">
            <blockpin signalname="a(3)" name="a" />
            <blockpin signalname="XLXN_3" name="ci" />
            <blockpin signalname="op(2:0)" name="op(2:0)" />
            <blockpin signalname="b(3)" name="b" />
            <blockpin signalname="r(3)" name="r" />
            <blockpin signalname="co" name="co" />
            <blockpin name="less" />
        </block>
        <block symbolname="m2_1" name="XLXI_6">
            <blockpin signalname="ci" name="D0" />
            <blockpin signalname="op(2)" name="D1" />
            <blockpin signalname="op(2)" name="S0" />
            <blockpin signalname="XLXN_32" name="O" />
        </block>
        <block symbolname="overflow_detect" name="XLXI_7">
            <blockpin signalname="op(2)" name="op" />
            <blockpin signalname="a(3)" name="a" />
            <blockpin signalname="b(3)" name="b" />
            <blockpin signalname="r(3)" name="r" />
            <blockpin signalname="overflow" name="overflow" />
        </block>
        <block symbolname="and4" name="XLXI_8">
            <blockpin signalname="XLXN_33" name="I0" />
            <blockpin signalname="XLXN_34" name="I1" />
            <blockpin signalname="XLXN_35" name="I2" />
            <blockpin signalname="XLXN_36" name="I3" />
            <blockpin signalname="zero" name="O" />
        </block>
        <block symbolname="inv4" name="XLXI_9">
            <blockpin signalname="r(3)" name="I0" />
            <blockpin signalname="r(2)" name="I1" />
            <blockpin signalname="r(1)" name="I2" />
            <blockpin signalname="r(0)" name="I3" />
            <blockpin signalname="XLXN_33" name="O0" />
            <blockpin signalname="XLXN_34" name="O1" />
            <blockpin signalname="XLXN_35" name="O2" />
            <blockpin signalname="XLXN_36" name="O3" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <instance x="1200" y="672" name="XLXI_2" orien="R0">
        </instance>
        <instance x="1200" y="1056" name="XLXI_3" orien="R0">
        </instance>
        <instance x="1200" y="1456" name="XLXI_4" orien="R0">
        </instance>
        <instance x="1200" y="1856" name="XLXI_5" orien="R0">
        </instance>
        <branch name="XLXN_1">
            <wire x2="1200" y1="896" y2="896" x1="1136" />
            <wire x2="1136" y1="896" y2="1184" x1="1136" />
            <wire x2="1664" y1="1184" y2="1184" x1="1136" />
            <wire x2="1664" y1="640" y2="640" x1="1584" />
            <wire x2="1664" y1="640" y2="832" x1="1664" />
            <wire x2="1664" y1="832" y2="1184" x1="1664" />
        </branch>
        <branch name="XLXN_2">
            <wire x2="1200" y1="1296" y2="1296" x1="1120" />
            <wire x2="1120" y1="1296" y2="1584" x1="1120" />
            <wire x2="1648" y1="1584" y2="1584" x1="1120" />
            <wire x2="1648" y1="1024" y2="1024" x1="1584" />
            <wire x2="1648" y1="1024" y2="1584" x1="1648" />
        </branch>
        <branch name="XLXN_3">
            <wire x2="1136" y1="1536" y2="1696" x1="1136" />
            <wire x2="1200" y1="1696" y2="1696" x1="1136" />
            <wire x2="1632" y1="1536" y2="1536" x1="1136" />
            <wire x2="1632" y1="1424" y2="1424" x1="1584" />
            <wire x2="1632" y1="1424" y2="1536" x1="1632" />
        </branch>
        <branch name="a(3:0)">
            <wire x2="272" y1="688" y2="688" x1="144" />
        </branch>
        <iomarker fontsize="28" x="144" y="688" name="a(3:0)" orien="R180" />
        <branch name="b(3:0)">
            <wire x2="288" y1="864" y2="864" x1="160" />
        </branch>
        <iomarker fontsize="28" x="160" y="864" name="b(3:0)" orien="R180" />
        <branch name="a(0)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="752" y="448" type="branch" />
            <wire x2="1200" y1="448" y2="448" x1="752" />
        </branch>
        <branch name="a(1)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="752" y="832" type="branch" />
            <wire x2="1200" y1="832" y2="832" x1="752" />
        </branch>
        <branch name="a(2)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="752" y="1232" type="branch" />
            <wire x2="1200" y1="1232" y2="1232" x1="752" />
        </branch>
        <branch name="a(3)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="768" y="1632" type="branch" />
            <wire x2="1200" y1="1632" y2="1632" x1="768" />
        </branch>
        <branch name="b(3)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="768" y="1824" type="branch" />
            <wire x2="1200" y1="1824" y2="1824" x1="768" />
        </branch>
        <branch name="b(2)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="752" y="1424" type="branch" />
            <wire x2="1200" y1="1424" y2="1424" x1="752" />
        </branch>
        <branch name="b(1)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="736" y="1024" type="branch" />
            <wire x2="1200" y1="1024" y2="1024" x1="736" />
        </branch>
        <branch name="b(0)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="752" y="640" type="branch" />
            <wire x2="1200" y1="640" y2="640" x1="752" />
        </branch>
        <branch name="r(3:0)">
            <wire x2="2144" y1="976" y2="976" x1="1968" />
        </branch>
        <iomarker fontsize="28" x="2144" y="976" name="r(3:0)" orien="R0" />
        <branch name="r(0)">
            <wire x2="1648" y1="448" y2="448" x1="1584" />
        </branch>
        <branch name="r(1)">
            <wire x2="1648" y1="832" y2="832" x1="1584" />
            <wire x2="1648" y1="832" y2="912" x1="1648" />
            <wire x2="1744" y1="912" y2="912" x1="1648" />
            <wire x2="1744" y1="832" y2="912" x1="1744" />
        </branch>
        <branch name="r(2)">
            <wire x2="1744" y1="1232" y2="1232" x1="1584" />
        </branch>
        <branch name="co">
            <wire x2="2080" y1="1824" y2="1824" x1="1584" />
        </branch>
        <iomarker fontsize="28" x="2080" y="1824" name="co" orien="R0" />
        <branch name="op(2:0)">
            <wire x2="304" y1="544" y2="544" x1="192" />
            <wire x2="304" y1="544" y2="576" x1="304" />
            <wire x2="400" y1="576" y2="576" x1="304" />
            <wire x2="400" y1="576" y2="960" x1="400" />
            <wire x2="464" y1="960" y2="960" x1="400" />
            <wire x2="1200" y1="960" y2="960" x1="464" />
            <wire x2="464" y1="960" y2="1360" x1="464" />
            <wire x2="512" y1="1360" y2="1360" x1="464" />
            <wire x2="1200" y1="1360" y2="1360" x1="512" />
            <wire x2="512" y1="1360" y2="1760" x1="512" />
            <wire x2="1200" y1="1760" y2="1760" x1="512" />
            <wire x2="800" y1="576" y2="576" x1="400" />
            <wire x2="1200" y1="576" y2="576" x1="800" />
        </branch>
        <iomarker fontsize="28" x="192" y="544" name="op(2:0)" orien="R180" />
        <branch name="r(3)">
            <wire x2="1200" y1="704" y2="704" x1="1152" />
            <wire x2="1152" y1="704" y2="1552" x1="1152" />
            <wire x2="1664" y1="1552" y2="1552" x1="1152" />
            <wire x2="1664" y1="1552" y2="1632" x1="1664" />
            <wire x2="1744" y1="1632" y2="1632" x1="1664" />
            <wire x2="1664" y1="1632" y2="1632" x1="1584" />
        </branch>
        <instance x="208" y="368" name="XLXI_6" orien="R0" />
        <branch name="op(2)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="128" y="336" type="branch" />
            <wire x2="208" y1="336" y2="336" x1="128" />
        </branch>
        <branch name="op(2)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="128" y="272" type="branch" />
            <wire x2="208" y1="272" y2="272" x1="128" />
        </branch>
        <branch name="ci">
            <wire x2="208" y1="208" y2="208" x1="112" />
        </branch>
        <iomarker fontsize="28" x="112" y="208" name="ci" orien="R180" />
        <branch name="XLXN_32">
            <wire x2="864" y1="240" y2="240" x1="528" />
            <wire x2="864" y1="240" y2="512" x1="864" />
            <wire x2="1200" y1="512" y2="512" x1="864" />
        </branch>
        <instance x="2480" y="1472" name="XLXI_7" orien="R0">
        </instance>
        <instance x="2208" y="704" name="XLXI_8" orien="R0" />
        <branch name="XLXN_33">
            <wire x2="2208" y1="640" y2="640" x1="2176" />
        </branch>
        <branch name="XLXN_34">
            <wire x2="2208" y1="576" y2="576" x1="2176" />
        </branch>
        <branch name="XLXN_35">
            <wire x2="2208" y1="512" y2="512" x1="2176" />
        </branch>
        <branch name="XLXN_36">
            <wire x2="2208" y1="448" y2="448" x1="2176" />
        </branch>
        <instance x="1952" y="672" name="XLXI_9" orien="R0" />
        <branch name="op(2)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="2384" y="1248" type="branch" />
            <wire x2="2480" y1="1248" y2="1248" x1="2384" />
        </branch>
        <branch name="a(3)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="2384" y="1312" type="branch" />
            <wire x2="2480" y1="1312" y2="1312" x1="2384" />
        </branch>
        <branch name="b(3)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="2384" y="1376" type="branch" />
            <wire x2="2480" y1="1376" y2="1376" x1="2384" />
        </branch>
        <branch name="r(3)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="2368" y="1440" type="branch" />
            <wire x2="2480" y1="1440" y2="1440" x1="2368" />
        </branch>
        <branch name="r(0)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="1888" y="448" type="branch" />
            <wire x2="1952" y1="448" y2="448" x1="1888" />
        </branch>
        <branch name="r(1)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="1888" y="512" type="branch" />
            <wire x2="1952" y1="512" y2="512" x1="1888" />
        </branch>
        <branch name="r(2)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="1888" y="576" type="branch" />
            <wire x2="1952" y1="576" y2="576" x1="1888" />
        </branch>
        <branch name="r(3)">
            <attrtext style="alignment:SOFT-RIGHT;fontsize:28;fontname:Arial" attrname="Name" x="1888" y="640" type="branch" />
            <wire x2="1952" y1="640" y2="640" x1="1888" />
        </branch>
        <branch name="zero">
            <wire x2="2544" y1="544" y2="544" x1="2464" />
        </branch>
        <iomarker fontsize="28" x="2544" y="544" name="zero" orien="R0" />
        <branch name="overflow">
            <wire x2="2896" y1="1248" y2="1248" x1="2864" />
        </branch>
        <iomarker fontsize="28" x="2896" y="1248" name="overflow" orien="R0" />
    </sheet>
</drawing>