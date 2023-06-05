import React from "react";
//import { Bar,Line } from "react-chartjs-2";

interface ChartData {
  labels: string[];
  datasets: {
    label: string;
    data: number[];
    backgroundColor: string;
  }[];
}

interface ChartProps {
  data: ChartData;
}

const TMTReport: React.FC<ChartProps> = ({ data }) => {
  const options = {
    scales: {
      y: {
        beginAtZero: true,
      },
    },
  };

  //return <Bar data={data} options={options} />;
  return null;
};

export default TMTReport;
