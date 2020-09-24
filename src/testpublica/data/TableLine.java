package testpublica.data;

/**
 *
 * @author Marcelo Augusto Mertz
 */
    public class TableLine {
        private int jogo, placar, minimo, maximo, rMin, rMax;
        
        /**
         * <b>Class Constructor</b><br>
         * Armazena as informações após o tratamento de ControleRecordes
         * 
         * @param id Índice ou jogo
         * @param pl placar do jogo
         * @param min minimo de pontos marcados 
         * @param max máximo de pontos marcados
         * @param rN número de vezes que o recorde de menor pontuação foi obtido
         * @param rX número de vezes que o recorde de maior pontuação foi obtido
         */
        public TableLine(int id, int pl, int min, int max, int rN, int rX){
            jogo = id;
            placar = pl;
            minimo = min;
            maximo = max;
            rMin = rN;
            rMax = rX;
        }
        
        /*************************************
        *                                    *
        *    BLOCO DE GETTERS AND SETTERS    *
        *                                    *
        *************************************/
        public void setJogo(int jogo){          this.jogo = jogo;   }
        public void setPlacar(int pl){          this.placar = pl;   }
        public void setMinimo (int min){        this.minimo = min;  }
        public void setMaximo (int max){        this.maximo = max;  }
        public void setRecordMinimo(int rN){    this.rMin = rN;     }
        public void setRecordMaximo(int rX){    this.rMax = rX;     }
        
        public int getJogo(){           return jogo;      }
        public int getPlacar(){         return placar;  }
        public int getMinimo(){         return minimo;  }
        public int getMaximo(){         return maximo;  }
        public int getRecorMinimo(){    return rMin;    }
        public int getRecordMaximo(){   return rMax;    }
        

}
