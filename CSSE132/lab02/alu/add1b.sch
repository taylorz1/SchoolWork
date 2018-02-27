<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="ci" />
        <signal name="XLXN_2" />
        <signal name="b" />
        <signal name="a" />
        <signal name="XLXN_89" />
        <signal name="XLXN_6" />
        <signal name="XLXN_90" />
        <signal name="XLXN_8" />
        <signal name="XLXN_91" />
        <signal name="XLXN_92" />
        <signal name="XLXN_93" />
        <signal name="XLXN_94" />
        <signal name="XLXN_95" />
        <signal name="XLXN_96" />
        <signal name="XLXN_97" />
        <signal name="XLXN_98" />
        <signal name="r" />
        <signal name="co" />
        <port polarity="Input" name="ci" />
        <port polarity="Input" name="b" />
        <port polarity="Input" name="a" />
        <port polarity="Output" name="r" />
        <port polarity="Output" name="co" />
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
        <blockdef name="and3">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-64" y2="-64" x1="0" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="64" y1="-192" y2="-192" x1="0" />
            <line x2="192" y1="-128" y2="-128" x1="256" />
            <line x2="144" y1="-176" y2="-176" x1="64" />
            <line x2="64" y1="-80" y2="-80" x1="144" />
            <arc ex="144" ey="-176" sx="144" sy="-80" r="48" cx="144" cy="-128" />
            <line x2="64" y1="-64" y2="-192" x1="64" />
        </blockdef>
        <blockdef name="or4">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="48" y1="-64" y2="-64" x1="0" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="64" y1="-192" y2="-192" x1="0" />
            <line x2="48" y1="-256" y2="-256" x1="0" />
            <line x2="192" y1="-160" y2="-160" x1="256" />
            <arc ex="112" ey="-208" sx="192" sy="-160" r="88" cx="116" cy="-120" />
            <line x2="48" y1="-208" y2="-208" x1="112" />
            <line x2="48" y1="-112" y2="-112" x1="112" />
            <line x2="48" y1="-256" y2="-208" x1="48" />
            <line x2="48" y1="-64" y2="-112" x1="48" />
            <arc ex="48" ey="-208" sx="48" sy="-112" r="56" cx="16" cy="-160" />
            <arc ex="192" ey="-160" sx="112" sy="-112" r="88" cx="116" cy="-200" />
        </blockdef>
        <blockdef name="or3">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="48" y1="-64" y2="-64" x1="0" />
            <line x2="72" y1="-128" y2="-128" x1="0" />
            <line x2="48" y1="-192" y2="-192" x1="0" />
            <line x2="192" y1="-128" y2="-128" x1="256" />
            <arc ex="192" ey="-128" sx="112" sy="-80" r="88" cx="116" cy="-168" />
            <arc ex="48" ey="-176" sx="48" sy="-80" r="56" cx="16" cy="-128" />
            <line x2="48" y1="-64" y2="-80" x1="48" />
            <line x2="48" y1="-192" y2="-176" x1="48" />
            <line x2="48" y1="-80" y2="-80" x1="112" />
            <arc ex="112" ey="-176" sx="192" sy="-128" r="88" cx="116" cy="-88" />
            <line x2="48" y1="-176" y2="-176" x1="112" />
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
        <block symbolname="and2" name="XLXI_28">
            <blockpin signalname="a" name="I0" />
            <blockpin signalname="ci" name="I1" />
            <blockpin signalname="XLXN_92" name="O" />
        </block>
        <block symbolname="and2" name="XLXI_29">
            <blockpin signalname="b" name="I0" />
            <blockpin signalname="ci" name="I1" />
            <blockpin signalname="XLXN_93" name="O" />
        </block>
        <block symbolname="and2" name="XLXI_30">
            <blockpin signalname="b" name="I0" />
            <blockpin signalname="a" name="I1" />
            <blockpin signalname="XLXN_94" name="O" />
        </block>
        <block symbolname="and3" name="XLXI_31">
            <blockpin signalname="b" name="I0" />
            <blockpin signalname="a" name="I1" />
            <blockpin signalname="ci" name="I2" />
            <blockpin signalname="XLXN_95" name="O" />
        </block>
        <block symbolname="and3" name="XLXI_32">
            <blockpin signalname="XLXN_90" name="I0" />
            <blockpin signalname="XLXN_89" name="I1" />
            <blockpin signalname="ci" name="I2" />
            <blockpin signalname="XLXN_96" name="O" />
        </block>
        <block symbolname="and3" name="XLXI_33">
            <blockpin signalname="XLXN_90" name="I0" />
            <blockpin signalname="a" name="I1" />
            <blockpin signalname="XLXN_91" name="I2" />
            <blockpin signalname="XLXN_97" name="O" />
        </block>
        <block symbolname="and3" name="XLXI_34">
            <blockpin signalname="b" name="I0" />
            <blockpin signalname="XLXN_89" name="I1" />
            <blockpin signalname="XLXN_91" name="I2" />
            <blockpin signalname="XLXN_98" name="O" />
        </block>
        <block symbolname="or4" name="XLXI_35">
            <blockpin signalname="XLXN_98" name="I0" />
            <blockpin signalname="XLXN_97" name="I1" />
            <blockpin signalname="XLXN_96" name="I2" />
            <blockpin signalname="XLXN_95" name="I3" />
            <blockpin signalname="r" name="O" />
        </block>
        <block symbolname="or3" name="XLXI_36">
            <blockpin signalname="XLXN_94" name="I0" />
            <blockpin signalname="XLXN_93" name="I1" />
            <blockpin signalname="XLXN_92" name="I2" />
            <blockpin signalname="co" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_42">
            <blockpin signalname="ci" name="I" />
            <blockpin signalname="XLXN_91" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_40">
            <blockpin signalname="a" name="I" />
            <blockpin signalname="XLXN_89" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_41">
            <blockpin signalname="b" name="I" />
            <blockpin signalname="XLXN_90" name="O" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <instance x="1104" y="512" name="XLXI_28" orien="R0" />
        <instance x="1088" y="752" name="XLXI_29" orien="R0" />
        <instance x="1104" y="1008" name="XLXI_30" orien="R0" />
        <instance x="1664" y="1280" name="XLXI_31" orien="R0" />
        <instance x="1664" y="1568" name="XLXI_32" orien="R0" />
        <instance x="1664" y="1840" name="XLXI_33" orien="R0" />
        <instance x="1664" y="2112" name="XLXI_34" orien="R0" />
        <instance x="2400" y="1712" name="XLXI_35" orien="R0" />
        <instance x="1712" y="768" name="XLXI_36" orien="R0" />
        <instance x="1136" y="1936" name="XLXI_42" orien="R0" />
        <branch name="ci">
            <wire x2="720" y1="384" y2="384" x1="672" />
            <wire x2="1104" y1="384" y2="384" x1="720" />
            <wire x2="720" y1="384" y2="624" x1="720" />
            <wire x2="1088" y1="624" y2="624" x1="720" />
            <wire x2="720" y1="624" y2="1088" x1="720" />
            <wire x2="1664" y1="1088" y2="1088" x1="720" />
            <wire x2="720" y1="1088" y2="1376" x1="720" />
            <wire x2="1664" y1="1376" y2="1376" x1="720" />
            <wire x2="720" y1="1376" y2="1904" x1="720" />
            <wire x2="1136" y1="1904" y2="1904" x1="720" />
        </branch>
        <branch name="b">
            <wire x2="656" y1="1216" y2="1776" x1="656" />
            <wire x2="656" y1="1776" y2="2048" x1="656" />
            <wire x2="1664" y1="2048" y2="2048" x1="656" />
            <wire x2="832" y1="1776" y2="1776" x1="656" />
            <wire x2="880" y1="1216" y2="1216" x1="656" />
            <wire x2="1664" y1="1216" y2="1216" x1="880" />
            <wire x2="880" y1="688" y2="688" x1="672" />
            <wire x2="880" y1="688" y2="944" x1="880" />
            <wire x2="1104" y1="944" y2="944" x1="880" />
            <wire x2="880" y1="944" y2="1216" x1="880" />
            <wire x2="1088" y1="688" y2="688" x1="880" />
        </branch>
        <instance x="784" y="1472" name="XLXI_40" orien="R0" />
        <instance x="832" y="1808" name="XLXI_41" orien="R0" />
        <branch name="a">
            <wire x2="784" y1="448" y2="448" x1="672" />
            <wire x2="1104" y1="448" y2="448" x1="784" />
            <wire x2="784" y1="448" y2="880" x1="784" />
            <wire x2="1104" y1="880" y2="880" x1="784" />
            <wire x2="784" y1="880" y2="1152" x1="784" />
            <wire x2="1664" y1="1152" y2="1152" x1="784" />
            <wire x2="784" y1="1152" y2="1152" x1="768" />
            <wire x2="768" y1="1152" y2="1440" x1="768" />
            <wire x2="784" y1="1440" y2="1440" x1="768" />
            <wire x2="768" y1="1440" y2="1712" x1="768" />
            <wire x2="1664" y1="1712" y2="1712" x1="768" />
        </branch>
        <branch name="XLXN_89">
            <wire x2="1088" y1="1440" y2="1440" x1="1008" />
            <wire x2="1664" y1="1440" y2="1440" x1="1088" />
            <wire x2="1088" y1="1440" y2="1984" x1="1088" />
            <wire x2="1664" y1="1984" y2="1984" x1="1088" />
        </branch>
        <branch name="XLXN_90">
            <wire x2="1424" y1="1776" y2="1776" x1="1056" />
            <wire x2="1664" y1="1776" y2="1776" x1="1424" />
            <wire x2="1664" y1="1504" y2="1504" x1="1424" />
            <wire x2="1424" y1="1504" y2="1776" x1="1424" />
        </branch>
        <branch name="XLXN_91">
            <wire x2="1472" y1="1904" y2="1904" x1="1360" />
            <wire x2="1664" y1="1904" y2="1904" x1="1472" />
            <wire x2="1664" y1="1904" y2="1920" x1="1664" />
            <wire x2="1664" y1="1648" y2="1648" x1="1472" />
            <wire x2="1472" y1="1648" y2="1904" x1="1472" />
        </branch>
        <branch name="XLXN_92">
            <wire x2="1712" y1="416" y2="416" x1="1360" />
            <wire x2="1712" y1="416" y2="576" x1="1712" />
        </branch>
        <branch name="XLXN_93">
            <wire x2="1520" y1="656" y2="656" x1="1344" />
            <wire x2="1520" y1="640" y2="656" x1="1520" />
            <wire x2="1712" y1="640" y2="640" x1="1520" />
        </branch>
        <branch name="XLXN_94">
            <wire x2="1712" y1="912" y2="912" x1="1360" />
            <wire x2="1712" y1="704" y2="912" x1="1712" />
        </branch>
        <branch name="XLXN_95">
            <wire x2="2400" y1="1152" y2="1152" x1="1920" />
            <wire x2="2400" y1="1152" y2="1456" x1="2400" />
        </branch>
        <branch name="XLXN_96">
            <wire x2="2160" y1="1440" y2="1440" x1="1920" />
            <wire x2="2160" y1="1440" y2="1520" x1="2160" />
            <wire x2="2400" y1="1520" y2="1520" x1="2160" />
        </branch>
        <branch name="XLXN_97">
            <wire x2="2160" y1="1712" y2="1712" x1="1920" />
            <wire x2="2160" y1="1584" y2="1712" x1="2160" />
            <wire x2="2400" y1="1584" y2="1584" x1="2160" />
        </branch>
        <branch name="XLXN_98">
            <wire x2="2400" y1="1984" y2="1984" x1="1920" />
            <wire x2="2400" y1="1648" y2="1984" x1="2400" />
        </branch>
        <branch name="r">
            <wire x2="2688" y1="1552" y2="1552" x1="2656" />
        </branch>
        <branch name="co">
            <wire x2="2000" y1="640" y2="640" x1="1968" />
        </branch>
        <iomarker fontsize="28" x="672" y="384" name="ci" orien="R180" />
        <iomarker fontsize="28" x="672" y="448" name="a" orien="R180" />
        <iomarker fontsize="28" x="672" y="688" name="b" orien="R180" />
        <iomarker fontsize="28" x="2688" y="1552" name="r" orien="R0" />
        <iomarker fontsize="28" x="2000" y="640" name="co" orien="R0" />
    </sheet>
</drawing>