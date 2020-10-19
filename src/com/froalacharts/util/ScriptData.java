package com.froalacharts.util;

public class ScriptData {
	public final static String chartData = "tsChart.setChartData({\n" + 
			"                data: dataTable,\n" + 
			"                \"yaxis\": [{\n" + 
			"                        \"plot\": {\n" + 
			"                            \"value\": \"Measure1\",\n" + 
			"                            \"type\": \"area\"\n" + 
			"                        },\n" + 
			"                        \"title\": \"area\",\n" + 
			"                        \"format\": {\n" + 
			"                            \"suffix\": '°C',\n" + 
			"                        },\n" + 
			"                        \"referenceLine\": [{\n" + 
			"                            \"label\": \"Ref Line\",\n" + 
			"                            \"value\": 0.5\n" + 
			"                        }],\n" + 
			"                        \"referenceZone\": [{\n" + 
			"                            \"label\": \"Ref Zone\",\n" + 
			"                            \"valueMax\": 0.4,\n" + 
			"                            \"valueMin\": 0.15\n" + 
			"                        }]\n" + 
			"                    },\n" + 
			"                    {\n" + 
			"                        \"plot\": {\n" + 
			"                            \"value\": \"Measure1\",\n" + 
			"                            \"type\": \"line\"\n" + 
			"                        },\n" + 
			"                        \"title\": \"line\"\n" + 
			"                    },\n" + 
			"                    {\n" + 
			"                        \"plot\": {\n" + 
			"                            \"value\": \"Measure1\",\n" + 
			"                            \"type\": \"smooth-line\"\n" + 
			"                        },\n" + 
			"                        \"title\": \"smooth-line\"\n" + 
			"                    },\n" + 
			"                    {\n" + 
			"                        \"plot\": {\n" + 
			"                            \"value\": \"Measure1\",\n" + 
			"                            \"type\": \"step-line\"\n" + 
			"                        },\n" + 
			"                        \"title\": \"step-line\"\n" + 
			"                    },\n" + 
			"                    {\n" + 
			"                        \"plot\": {\n" + 
			"                            \"value\": \"Measure1\",\n" + 
			"                            \"type\": \"column\"\n" + 
			"                        },\n" + 
			"                        \"title\": \"column\"\n" + 
			"                    },\n" + 
			"                    {\n" + 
			"                        \"plot\": {\n" + 
			"                            \"value\": \"Measure1\",\n" + 
			"                            \"type\": \"smooth-area\"\n" + 
			"                        },\n" + 
			"                        \"title\": \"smooth-area\"\n" + 
			"                    },\n" + 
			"                    {\n" + 
			"                        \"plot\": {\n" + 
			"                            \"value\": \"Measure1\",\n" + 
			"                            \"type\": \"step-area\"\n" + 
			"                        },\n" + 
			"                        \"title\": \"step-area\"\n" + 
			"                    },\n" + 
			"                    {\n" + 
			"                        \"plot\": {\n" + 
			"                            \"value\": {\n" + 
			"                                open: \"Measure1\",\n" + 
			"                                high: \"Measure2\",\n" + 
			"                                low: \"Measure3\",\n" + 
			"                                close: \"Measure4\"\n" + 
			"                            },\n" + 
			"                            \"type\": \"candlestick\"\n" + 
			"                        },\n" + 
			"                        \"title\": \"candlestick\"\n" + 
			"                    },\n" + 
			"                    {\n" + 
			"                        \"plot\": {\n" + 
			"                            \"value\": {\n" + 
			"                                open: \"Measure1\",\n" + 
			"                                high: \"Measure2\",\n" + 
			"                                low: \"Measure3\",\n" + 
			"                                close: \"Measure4\"\n" + 
			"                            },\n" + 
			"                            \"type\": \"ohlc\"\n" + 
			"                        },\n" + 
			"                        \"title\": \"ohlc\"\n" + 
			"                    }\n" + 
			"\n" + 
			"                ],\n" + 
			"                \"xAxis\": {\n" + 
			"                    \"timemarker\": [{\n" + 
			"                        \"start\": '1980-12-23',\n" + 
			"                        \"label\": 'This is a time marker',\n" + 
			"                        \"timeFormat\": \"%Y-%m-%d\",\n" + 
			"                        \"end\": \"1980-12-24\"\n" + 
			"                    }],\n" + 
			"                },\n" + 
			"                \"caption\": {\n" + 
			"                    \"text\": \"I am a caption\"\n" + 
			"                },\n" + 
			"                \"subcaption\": {\n" + 
			"                    \"text\": \"I am a subcaption\"\n" + 
			"                },\n" + 
			"                \"chart\": {\n" + 
			"                    \"animation\": 0,\n" + 
			"                    \"exportenabled\": 1,\n" + 
			"                    \"enableChartMouseMoveEvent\": 1,\n" + 
			"                    \"animation\": 1,\n" + 
			"                    \"showprintmenuitem\": 1\n" + 
			"                },\n" + 
			"                \"dataMarker\": [{\n" + 
			"                    \"series\": \"Country\",\n" + 
			"                    \"time\": \"1980-12-19\",\n" + 
			"                    \"type\": \"flag\", //data Marker set to pin\n" + 
			"                    \"identifier\": \"D\",\n" + 
			"                    \"timeFormat\": \"%Y-%m-%d\",\n" + 
			"                    \"tooltext\": \"This is a Data Marker\",\n" + 
			"                    \"value\": \"Measure1\"\n" + 
			"                }]\n" + 
			"            })";
	
	public final static String addEventListener = "FroalaCharts.addEventListener(\"dataplotRollOver\", handler);";
	
	public final static String removeEventListener = "FroalaCharts.removeEventListener(\"dataplotRollOver\", handler);";
	
	public final static String getObjectReference = "var chart = FroalaCharts.getObjectReference(\"froalatimeid\");\n" + 
			"        chart.innerHTML = \"<span>The svg element of the chart is replaced by this text</span>\";";
}
