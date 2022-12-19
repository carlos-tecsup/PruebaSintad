export interface Entidad{
    id_entidad?: number;
    id_tipo_documento: number;
    nombre_comercial?: string;
    nro_documento: string;
    razon_social: string;
    telefono?: string;
    id_tipo_contribuyente?: number;
    direccion?: string;
    estado: boolean
}