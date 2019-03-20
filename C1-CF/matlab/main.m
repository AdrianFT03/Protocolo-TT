function varargout = main(varargin)
% MAIN MATLAB code for main.fig
%      MAIN, by itself, creates a new MAIN or raises the existing
%      singleton*.
%
%      H = MAIN returns the handle to a new MAIN or the handle to
%      the existing singl   eton*.
%
%      MAIN('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in MAIN.M with the given input arguments.
%
%      MAIN('Property','Value',...) creates a new MAIN or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before main_OpeningFcn gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to main_OpeningFcn via varargin.
% 
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help main

% Last Modified by GUIDE v2.5 23-Oct-2018 20:15:46

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @main_OpeningFcn, ...
                   'gui_OutputFcn',  @main_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT


% --- Executes just before main is made visible.
function main_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to main (see VARARGIN)

% Choose default command line output for main
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);

% UIWAIT makes main wait for user response (see UIRESUME)
% uiwait(handles.figure1);


% --- Outputs from this function are returned to the command line.
function varargout = main_OutputFcn(hObject, eventdata, handles) 
% varargout  cell array for returning output args (see VARARGOUT);
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get default command line output from handles structure
varargout{1} = handles.output;


% --- Executes on button press in pushbutton1.
function pushbutton1_Callback(hObject, eventdata, handles)
% hObject    handle to pushbutton1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

    %Mostramos un selector de archivos para obtener la dirección y nombre del
    %archivo con los datos de la señal
    [file,path] = uigetfile({'*.txt';'*.dat';'*.*'}, ...
    'Seleccione un archivo','C:\Users\Sola\Downloads\');
    if isequal(file,0)
       disp('User selected Cancel');
    else
       disp(['User selected ', fullfile(path,file)]);

       set(handles.nombre, 'String', file);

       %Obtenemos los datos del archivo pero en forma de array
       y=importdata(fullfile(path,file));

       %Obtenemos un vector de los datos para poder utilizarlo con otras funciones
       global signal_or;
       signal_or = y(:);
    end


function edit1_Callback(hObject, eventdata, handles)
% hObject    handle to edit1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of edit1 as text
%        str2double(get(hObject,'String')) returns contents of edit1 as a double


% --- Executes during object creation, after setting all properties.
function edit1_CreateFcn(hObject, eventdata, handles)
% hObject    handle to edit1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function edit2_Callback(hObject, eventdata, handles)
% hObject    handle to edit2 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of edit2 as text
%        str2double(get(hObject,'String')) returns contents of edit2 as a double


% --- Executes during object creation, after setting all properties.
function edit2_CreateFcn(hObject, eventdata, handles)
% hObject    handle to edit2 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in pushbutton2.
function pushbutton2_Callback(hObject, eventdata, handles)
% hObject    handle to pushbutton2 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
    global signal_or;
%Graficamos la señal original normalizada
   %plot(handles.axes1, (signal-min(signal))/(max(signal)-min(signal)));
   plot(handles.axes1, signal_or);
   
   %Obtener coeficientes de la ventana del tamaño de la señal
   switch get(get(handles.uibuttongroup1,'SelectedObject'),'Tag')
       case 'radiobutton1'
           disp("Bartlett-Hann");
           w = barthannwin(length(signal_or));
           
       case 'radiobutton2'
           disp("Bartlett");
           w = bartlett(length(signal_or));
           
       case 'radiobutton3'
           disp("Blackman");
           w = blackman(length(signal_or));
           
       case 'radiobutton4'
           disp("Blackman-Harris");
           w = blackmanharris(length(signal_or));
           
       case 'radiobutton5'
           disp("Bohammhman");
           w = bohmanwin(length(signal_or));
           
       case 'radiobutton6'
           disp("Chebyshev");
           w = chebwin(length(signal_or));
           
       case 'radiobutton7'
           disp("Flat Top");
            w = flattopwin(length(signal_or));
           
       case 'radiobutton8'
           disp("Gaussian");
           w = gausswin(length(signal_or));
           
       case 'radiobutton9'
           disp("Hamming");
           w = hamming(length(signal_or));
           
       case 'radiobutton10'
           disp("Hanning");
           w = hann(length(signal_or));
           
       case 'radiobutton11'
           disp("Rectangular");
           w = rectwin(length(signal_or));
           
       case 'radiobutton12'
           disp("Taylor");
           w = taylorwin(length(signal_or));
   end
   
   %Graficamos la ventana
   plot(handles.axes2, w);
   
   %Rango máximo del ADC (12 bits = 4096)
   offset = 4096/2;
   
   %Configuración frecuencia muestreo 
   fs = 512
   
   %Haciendo offset a la señal
   for i = 1:length(signal_or)
       signal(i) = signal_or(i) - offset;
   end
   
   %Normalizando la señal
   max_sig = max(signal);
   signal = signal.*(1/max_sig);
   
   plot(handles.axes1, signal);
   
   %Filtrando la señal con la ventana
   %Obtenemos el filtro con la ventana
   %signal_w = conv(signal, b);
   signal_w = [];
   for i = 1:length(signal)-1
       signal_w(end+1) = signal(i) * w(i);
       %fprintf("%f = %d * %f en %d \n", signal_w(i),signal(i), w(i), i);
   end
   
   %Graficamos la señal filtrada
   plot(handles.axes3, signal_w);
   %Normalizada
   %plot(handles.axes3, (signal_w-min(signal_w))/(max(signal_w)-min(signal_w)));
   
   %Autocorrelación de la señal filtrada
   signal_ac = autocorr(signal_w, length(signal_w)-1);
   %signal_ac = autocorr(signal, length(signal)-1);
   
   %Mostrando la frecuencia como eje x: esc = fs/2/N
   delta = fs/2/length(signal_w);
   esc = delta * (0:length(signal_w)-1);
   
   plot(handles.axes4, signal_ac);
   
  %para frecuencia frec muestreo / posicion 
   
  %FFT de la señal filtrada
   signal_fft = fft(signal_w);
   
   %P2 = abs(signal_fft/length(signal_fft));
   P1 = abs(signal_fft)
   %P1 = P2(1:length(P2)/2+1);
   %P1(2:end-1) = 2*P1(2:end-1);
  
   %Mostrando la frecuencia como eje x: esc = fs/2/N
   delta = fs/2/length(P1);
   esc = delta * (0:length(P1)-1);
   
   plot(handles.axes5, (P1-min(P1))/(max(P1)-min(P1)));
   
   %Busca los máximos
   maximos=[];
   posicion=[];
   
   for i = 2:length(signal_w)-1
       %fprintf("Valor = %f \n", signal_ac(i));
       if (signal_ac(i) > signal_ac(i-1)) && (signal_ac(i) > signal_ac(i+1))
        maximos(end+1)=signal_ac(i);
        posicion(end+1)=i;
        fprintf("MAX = %0.4f en %d \n", signal_ac(i), i);
       end
   end
   [M,I] = max(maximos);
   %fprintf("Valor máximo autocorr D = %0.4f en %d; F = %0.4f Hz \n", M, posicion(I), posicion(I)*delta);
   
   %Cálculo de frecuencia
   f = 1/(posicion(I)*(length(signal_ac)/4)/length(signal_ac));
   
   %fprintf("Valor máximo autocorr = %0.4f en %d; F = %0.4f Hz -> %d ppm \n", M, posicion(I), (512*posicion(I))/length(signal_ac), round((512*posicion(I))/length(signal_ac)*60));
   fprintf("Valor máximo autocorr = %0.4f en %d; F = %0.4f Hz -> %d lpm \n", M, posicion(I), f, round(f*60));
   fprintf("Valor máximo autocorr = %0.4f en %d; F = %0.4f Hz -> %d lpm \n", M, posicion(I), fs/posicion(I), fs/posicion(I)*60);
   
   set(handles.resultadoAC, 'String', sprintf("Valor máximo autocorr = %0.4f en %d; F = %0.4f Hz -> %d lpm \n", M, posicion(I), fs/posicion(I), round(fs/posicion(I)*60)));
   
   maximos1=[];
   posicion1=[];
   
   for i = 2:length(P1)-1
       %fprintf("Valor = %f \n", signal_ac(i));
       if (P1(i) > P1(i-1)) && (P1(i) > P1(i+1))
        maximos1(end+1)=P1(i);
        posicion1(end+1)=i;
        %fprintf("MAX = %0.4f en %d \n", P1(i), i);
       end
   end
   [M1,I1] = max(maximos1);
   %fprintf("Valor máximo fft D = %0.4f en %d; F = %0.4f Hz \n", M1, posicion1(I1), posicion1(I1)*delta);
   fprintf("Valor máximo fft = %0.4f en %d; F = %0.4f Hz -> %d lpm  \n", M1, posicion1(I1), (512*posicion1(I1))/length(P1), round((fs*posicion1(I1))/length(P1)*60));
   
   set(handles.resultadoFFT, 'String', sprintf("Valor máximo fft = %0.4f en %d; F = %0.4f Hz -> %d lpm  \n", M1, posicion1(I1), (fs*posicion1(I1))/length(P1), round((fs*posicion1(I1))/length(P1)*60)));
