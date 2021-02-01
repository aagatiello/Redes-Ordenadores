#!/bin/sh

# 
# Vivado(TM)
# runme.sh: a Vivado-generated Runs Script for UNIX
# Copyright 1986-2018 Xilinx, Inc. All Rights Reserved.
# 

if [ -z "$PATH" ]; then
  PATH=/home/agus/tools/Xilinx/SDK/2018.3/bin:/home/agus/tools/Xilinx/Vivado/2018.3/ids_lite/ISE/bin/lin64:/home/agus/tools/Xilinx/Vivado/2018.3/bin
else
  PATH=/home/agus/tools/Xilinx/SDK/2018.3/bin:/home/agus/tools/Xilinx/Vivado/2018.3/ids_lite/ISE/bin/lin64:/home/agus/tools/Xilinx/Vivado/2018.3/bin:$PATH
fi
export PATH

if [ -z "$LD_LIBRARY_PATH" ]; then
  LD_LIBRARY_PATH=/home/agus/tools/Xilinx/Vivado/2018.3/ids_lite/ISE/lib/lin64
else
  LD_LIBRARY_PATH=/home/agus/tools/Xilinx/Vivado/2018.3/ids_lite/ISE/lib/lin64:$LD_LIBRARY_PATH
fi
export LD_LIBRARY_PATH

HD_PWD='/home/agus/Downloads/Practica1Redes/Practica1Redes.runs/synth_1'
cd "$HD_PWD"

HD_LOG=runme.log
/bin/touch $HD_LOG

ISEStep="./ISEWrap.sh"
EAStep()
{
     $ISEStep $HD_LOG "$@" >> $HD_LOG 2>&1
     if [ $? -ne 0 ]
     then
         exit
     fi
}

EAStep vivado -log transmission.vds -m64 -product Vivado -mode batch -messageDb vivado.pb -notrace -source transmission.tcl
