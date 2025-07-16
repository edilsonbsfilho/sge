export interface Evento {
  id?: number;
  titulo: string;
  descricao: string;
  dataHoraEvento: string;
  local: string;
  criadoEm?: string;
  atualizadoEm?: string;
}